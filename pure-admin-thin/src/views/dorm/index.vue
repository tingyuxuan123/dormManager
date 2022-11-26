<template>
  <div class="dorm">
    <header>
      <el-row :gutter="20">
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
          <el-button type="primary" @click="addStudnet">添加宿舍</el-button>
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
        <el-table-column prop="ID" label="id" width="180" />
        <el-table-column prop="BUILD_NAME" label="宿舍楼" />
        <el-table-column prop="DOR_NAME" label="宿舍" />
        <el-table-column prop="NUM" label="房间人数" />
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
            <el-form-item label="宿舍楼名称" label-width="120px">
              <el-select
                v-model="form.BUILD_ID"
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
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="宿舍人数" label-width="120px">
              <el-input
                v-model="form.NUM"
                placeholder="请输入宿舍人数"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="宿舍名" label-width="120px">
              <el-input
                v-model="form.DOR_NAME"
                placeholder="请输入宿舍名"
              /> </el-form-item
          ></el-col>
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
} from "@/api/dorm";
import { Delete, Edit, Search, Share, Upload } from "@element-plus/icons-vue";
import { buildingSelectListApi } from "@/api/building";
import { dormSelectListApi } from "@/api/dorm";
import { ElMessage } from "element-plus";

const buildingId = localStorage.getItem("buildingId") === "0" ? false : true;

const queryQarams = reactive({
  dormitoryId: undefined,
  currentPage: 1,
  pageSize: 10
});

const form = reactive({
  ID: undefined,
  BUILD_ID: undefined,
  DOR_NAME: undefined,
  NUM: undefined
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
  title.value = "添加宿舍";

  form.ID = undefined;
  form.DOR_NAME = undefined;
  form.NUM = undefined;
  form.BUILD_ID = undefined;

  if (buildingId) {
    form.BUILD_ID = JSON.parse(localStorage.getItem("buildingId"));
  }

  ADDvisible.value = true;
};

const onSubmit = () => {
  if (title.value == "添加宿舍") {
    insertStudent();
  } else {
    editStudent();
  }
};

const handleEdit = (scope: any) => {
  console.log(scope);

  form.ID = scope.ID;
  form.BUILD_ID = scope.BUILD_ID;
  form.NUM = scope.NUM;
  form.DOR_NAME = scope.DOR_NAME;

  title.value = "修改宿舍信息";
  ADDvisible.value = true;
};

const handleDelete = async (scope: any) => {
  const res = await deleteStudentApi(scope.ID);
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
  queryQarams.dormitoryId = undefined;

  search();
};

const currentPageChange = (currentPage: number) => {
  console.log(currentPage);
  queryQarams.currentPage = currentPage;
  getStudentList();
};
</script>

<style scoped lang="scss">
.dorm {
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
