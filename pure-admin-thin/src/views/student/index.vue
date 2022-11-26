<template>
  <div class="studnetpage">
    <header>
      <el-row :gutter="20">
        <el-col :span="5">
          <el-input
            v-model="queryQarams.studentName"
            placeholder="请输入要搜索的学生姓名"
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
        <el-col :span="4">
          <el-select
            v-model="queryQarams.dormitoryId"
            filterable
            placeholder="请选宿舍名"
          >
            <el-option
              v-for="item in dormList"
              :key="item.ID"
              :label="item.DOR_NAME"
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
          <el-button type="primary" @click="addStudnet">添加学生</el-button>
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
        <el-table-column prop="studentEntity.ID" label="学号" width="180" />
        <el-table-column prop="studentEntity.NAME" label="姓名" />
        <el-table-column prop="studentEntity.SEX" label="性别" />
        <el-table-column prop="studentEntity.AGE" label="年龄" />
        <el-table-column prop="studentEntity.DEPARTMENT" label="院系" />
        <el-table-column prop="studentEntity.TELEPHONE" label="电话号码" />
        <el-table-column prop="buildingEntity.BUILD_NAME" label="宿舍楼" />
        <el-table-column prop="dormitoryEntity.DOR_NAME" label="宿舍名" />
        <el-table-column prop="bunk" label="床位号" width="80" />
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
            <el-form-item label="学号" label-width="80px">
              <el-input
                v-model="form.studentEntity.ID"
                placeholder="请输入学生学号"
              /> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="姓名" label-width="80px">
              <el-input
                v-model="form.studentEntity.NAME"
                placeholder="请输入学生姓名"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="性别" label-width="80px">
              <el-input
                v-model="form.studentEntity.SEX"
                placeholder="请输入性别"
              /> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="年龄" label-width="80px">
              <el-input-number
                v-model="form.studentEntity.AGE"
                :min="10"
                :max="100"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="院系" label-width="80px">
              <el-input
                v-model="form.studentEntity.DEPARTMENT"
                placeholder="请输入院系"
              /> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="电话号码" label-width="80px">
              <el-input
                v-model="form.studentEntity.TELEPHONE"
                placeholder="请输入学生电话号码"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="宿舍楼" label-width="80px">
              <el-select
                v-model="form.buildingEntity.ID"
                filterable
                placeholder="请选择宿舍楼"
                :disabled="buildingId"
              >
                <el-option
                  v-for="item in buildingList"
                  :key="item.ID"
                  :label="item.BUILD_NAME"
                  :value="item.ID"
                />
              </el-select> </el-form-item
          ></el-col>
          <el-col :span="12">
            <el-form-item label="宿舍名" label-width="80px">
              <el-select
                v-model="form.dormitoryEntity.ID"
                filterable
                placeholder="请选择宿舍名"
              >
                <el-option
                  v-for="item in dormList"
                  :key="item.ID"
                  :label="item.DOR_NAME"
                  :value="item.ID"
                />
              </el-select> </el-form-item
          ></el-col>
        </el-row>

        <el-form-item label="床位号" label-width="80px">
          <el-input v-model="form.BUNK" placeholder="请输入床位号" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button>取消</el-button>
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
} from "@/api/student";
import { Delete, Edit, Search, Share, Upload } from "@element-plus/icons-vue";
import { buildingSelectListApi } from "@/api/building";
import { dormSelectListApi } from "@/api/dorm";
import { ElMessage } from "element-plus";

const buildingId = localStorage.getItem("buildingId") === "0" ? false : true;

const queryQarams = reactive({
  studentName: undefined,
  buildingId: undefined,
  dormitoryId: undefined,
  currentPage: 1,
  pageSize: 10
});

const form = reactive({
  studentEntity: {
    ID: undefined,
    NAME: undefined,
    SEX: undefined,
    AGE: 10,
    DEPARTMENT: undefined,
    TELEPHONE: undefined
  },
  buildingEntity: {
    ID: undefined,
    BUILD_NAME: undefined,
    DESCRIBE: undefined
  },
  dormitoryEntity: {
    ID: undefined,
    BUILD_ID: undefined,
    NUM: undefined,
    DOR_NAME: undefined
  },
  BUNK: undefined,
  id: undefined
});

const title = ref("添加学生");

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
  title.value = "添加学生";

  form.studentEntity.NAME = undefined;
  form.studentEntity.AGE = undefined;
  form.studentEntity.ID = undefined;
  form.studentEntity.SEX = undefined;
  form.studentEntity.DEPARTMENT = undefined;
  form.studentEntity.TELEPHONE = undefined;
  form.buildingEntity.BUILD_NAME = undefined;
  form.buildingEntity.ID = undefined;
  form.dormitoryEntity.DOR_NAME = undefined;
  form.dormitoryEntity.ID = undefined;
  form.BUNK = undefined;

  if (buildingId) {
    form.buildingEntity.ID = JSON.parse(localStorage.getItem("buildingId"));
  }

  ADDvisible.value = true;
};

const onSubmit = () => {
  if (title.value == "添加学生") {
    insertStudent();
  } else {
    editStudent();
  }
};

const handleEdit = (scope: any) => {
  console.log(scope);

  form.studentEntity.NAME = scope.studentEntity.NAME;
  form.studentEntity.AGE = scope.studentEntity.AGE;
  form.studentEntity.ID = scope.studentEntity.ID;
  form.studentEntity.SEX = scope.studentEntity.SEX;
  form.studentEntity.DEPARTMENT = scope.studentEntity.DEPARTMENT;
  form.studentEntity.TELEPHONE = scope.studentEntity.TELEPHONE;

  form.buildingEntity.BUILD_NAME = scope.buildingEntity.BUILD_NAME;
  form.buildingEntity.ID = scope.buildingEntity.ID;
  form.dormitoryEntity.DOR_NAME = scope.dormitoryEntity.DOR_NAME;
  form.dormitoryEntity.ID = scope.dormitoryEntity.ID;
  form.BUNK = scope.bunk;
  form.id = scope.id;

  title.value = "修改学生信息";
  ADDvisible.value = true;
};

const handleDelete = async (scope: any) => {
  const res = await deleteStudentApi(scope.id);
  ElMessage({
    message: "删除成功",
    type: "success"
  });
};

const search = () => {
  getStudentList();
};

const reset = () => {
  queryQarams.studentName = undefined;
  queryQarams.dormitoryId = undefined;
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
.studnetpage {
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
