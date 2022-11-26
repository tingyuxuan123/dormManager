import request from "@/utils/request";

export const buildingSelectListApi = () => {
  return request("/building/selectList", "get");
};

export const studentListApi = (queryParams: object) => {
  return request("/building/list", "post", {
    ...queryParams
  });
};

export const insertStudentApi = (buildingInfo: object) => {
  return request("/building/insertBuilding", "post", {
    ...buildingInfo
  });
};

export const editStudentApi = (buildingInfo: object) => {
  return request("/building/editBuilding", "post", {
    ...buildingInfo
  });
};

export const deleteStudentApi = (id: number) => {
  return request("/building/deleteBuilding", "get", {
    id
  });
};
