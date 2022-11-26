<template>
  <div class="housemaster">
    <header>
      <el-row :gutter="20">
        <el-col :span="5">
          <el-input
            v-model="queryQarams.name"
            placeholder="请输入要搜索的管理员姓名"
            clearable
          />
        </el-col>
        <el-col :span="4">
          <el-select
            v-model="queryQarams.buildingId"
            filterable
            placeholder="请选择宿舍楼"
          >
            <el-option
              v-for="item in buildingList"
              :key="item.ID"
              :label="item.BUILD_NAME"
              :value="item.ID"
            />
          </el-select>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="search">搜索</el-button>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" @click="reset">重置</el-button>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addStudnet">添加管理员</el-button>
        </el-col>
      </el-row>
    </header>

    <div class="content">
      <el-table
        :data="tableData"
        border
        style="width: 100%"
        v-loading="loading"
      >
        <el-table-column prop="id" label="id" width="180" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="age" label="年龄" />
        <el-table-column prop="buildingName" label="管理区域" />
        <el-table-column prop="telephone" label="电话号码" />
        <el-table-column prop="address" label="地址" />
        <el-table-column fixed="right" label="操作" width="220">
          <template #default="scope">
            <el-button
              type="primary"
              :icon="Edit"
              @click="handleEdit(scope.row)"
              >修改</el-button
            >
            <el-button
              type="danger"
              :icon="Delete"
              @click="handleDelete(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <div class="pagination">
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        @current-change="currentPageChange"
      />
    </div>

    <el-dialog v-model="ADDvisible" :show-close="true">
      <template #header="{ close, titleId, titleClass }">
        <div class="my-header">
          <h4 :id="titleId" :class="titleClass">{{ title }}</h4>
        </div>
      </template>
      <el-form :model="form">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名" label-width="80px">
              <el-input
                v-model="form.name"
                placeholder="请输入姓名"
              /> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="年龄" label-width="80px">
              <el-input-number
                v-model="form.age"
                :min="10"
                :max="100"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别" label-width="80px">
              <el-input
                v-model="form.sex"
                placeholder="请数入性别"
              /> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="电话号码" label-width="80px">
              <el-input
                v-model="form.telephone"
                placeholder="请输入电话号码"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="管理楼" label-width="80px">
              <el-select
                v-model="form.manage_AREA"
                filterable
                placeholder="请选择宿舍楼"
              >
                <el-option
                  v-for="item in buildingList"
                  :key="item.ID"
                  :label="item.BUILD_NAME"
                  :value="item.ID"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址" label-width="80px">
              <el-input
                v-model="form.address"
                placeholder="请输入地址"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="密码" label-width="80px">
              <el-input v-model="form.password" placeholder="密码" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="ADDvisible = false">取消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import {
  studentListApi,
  insertStudentApi,
  editStudentApi,
  deleteStudentApi
} from "@/api/admin";
import { Delete, Edit, Search, Share, Upload } from "@element-plus/icons-vue";
import { buildingSelectListApi } from "@/api/building";
import { dormSelectListApi } from "@/api/dorm";
import { ElMessage } from "element-plus";

const queryQarams = reactive({
  name: undefined,
  buildingId: undefined,
  currentPage: 1,
  pageSize: 10
});

const form = reactive({
  id: undefined,
  name: undefined,
  sex: undefined,
  age: undefined,
  manage_AREA: undefined,
  telephone: undefined,
  address: undefined,
  buildingName: undefined,
  password: undefined
});

const title = ref("添加管理员");

const loading = ref(false);

const ADDvisible = ref(false);

const tableData = ref([]);

const total = ref(0);

const getStudentList = async () => {
  loading.value = true;
  const res: any = await studentListApi(queryQarams);
  tableData.value = res.data.data.data;
  total.value = res.data.data.total;
  loading.value = false;
};

getStudentList();

const insertStudent = async () => {
  const res = await insertStudentApi(form);
  if (res.data.code === 200) {
    ElMessage({
      message: "添加成功！",
      type: "success"
    });
    getStudentList();
  }
  ADDvisible.value = false;
};

const editStudent = async () => {
  const res = await editStudentApi(form);
  ElMessage({
    message: "修改成功！",
    type: "success"
  });

  getStudentList();
  ADDvisible.value = false;
};

const buildingList = ref([]);
//获取宿舍楼列表
const buildingSelectList = async () => {
  const res = await buildingSelectListApi();
  buildingList.value = res.data.data;
  console.log(buildingList.value);
};

buildingSelectList();

const dormList = ref([]);
//获取宿舍楼列表
const dormSelectList = async () => {
  const res = await dormSelectListApi();
  dormList.value = res.data.data;
  console.log(dormList.value);
};

dormSelectList();

const addStudnet = () => {
  title.value = "添加管理员";

  form.id = undefined;
  form.name = undefined;
  form.sex = undefined;
  form.age = undefined;
  form.manage_AREA = undefined;
  form.telephone = undefined;
  form.address = undefined;
  form.password = undefined;

  ADDvisible.value = true;
};

const onSubmit = () => {
  if (title.value == "添加管理员") {
    insertStudent();
  } else {
    editStudent();
  }
};

const handleEdit = (scope: any) => {
  console.log(scope);

  form.id = scope.id;
  form.name = scope.name;
  form.sex = scope.sex;
  form.age = scope.age;
  form.manage_AREA = scope.manage_AREA;
  form.buildingName = scope.buildingName;
  form.telephone = scope.telephone;
  form.address = scope.address;
  form.password = scope.password;
  title.value = "修改管理员信息";
  ADDvisible.value = true;
};

const handleDelete = async (scope: any) => {
  const res = await deleteStudentApi(scope.id);
  getStudentList();
  ADDvisible.value = false;
  ElMessage({
    message: "删除成功",
    type: "success"
  });
};

const search = () => {
  getStudentList();
};

const reset = () => {
  queryQarams.name = undefined;
  queryQarams.buildingId = undefined;

  search();
};

const currentPageChange = (currentPage: number) => {
  console.log(currentPage);
  queryQarams.currentPage = currentPage;
  getStudentList();
};
</script>

<style scoped lang="scss">
.housemaster {
  height: 680px;
  background-color: #fff;
  padding: 20px;
}

.content {
  margin-top: 20px;
}

.pagination {
  margin-top: 10px;
}

.my-header {
  margin: 0px;
}

.handle {
  width: 100%;
  display: flex;
  justify-content: flex-end;
}
</style>
