<template>
  <div class="building">
    <header>
      <el-row :gutter="20">
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
          <el-button type="primary" @click="addStudnet">添加宿舍楼</el-button>
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
        <el-table-column prop="DESCRIBE" label="描述" />
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
          <el-col :span="24">
            <el-form-item label="宿舍楼名称" label-width="120px">
              <el-input
                v-model="form.BUILD_NAME"
                placeholder="请输入宿舍楼名称"
              /> </el-form-item
          ></el-col>
        </el-row>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="描述简介" label-width="120px">
              <el-input
                type="textarea"
                v-model="form.DESCRIBE"
                :autosize="{ minRows: 4, maxRows: 6 }"
                placeholder="请输入简介"
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
} from "@/api/building";
import { Delete, Edit, Search, Share, Upload } from "@element-plus/icons-vue";
import { buildingSelectListApi } from "@/api/building";
import { dormSelectListApi } from "@/api/dorm";
import { ElMessage } from "element-plus";

const queryQarams = reactive({
  buildingId: undefined,
  currentPage: 1,
  pageSize: 10
});

const form = reactive({
  ID: undefined,
  BUILD_NAME: undefined,
  DESCRIBE: undefined
});

const title = ref("添加宿舍楼");

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
  title.value = "添加宿舍楼";

  form.ID = undefined;
  form.BUILD_NAME = undefined;
  form.DESCRIBE = undefined;

  ADDvisible.value = true;
};

const onSubmit = () => {
  if (title.value == "添加宿舍楼") {
    insertStudent();
  } else {
    editStudent();
  }
};

const handleEdit = (scope: any) => {
  console.log(scope);

  form.ID = scope.ID;
  form.BUILD_NAME = scope.BUILD_NAME;
  form.DESCRIBE = scope.DESCRIBE;

  title.value = "修改宿舍楼信息";
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
.building {
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
