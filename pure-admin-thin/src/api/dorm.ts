import request from "@/utils/request";

export const dormSelectListApi = () => {
  return request("/dormitory/selectList", "get");
};

export const studentListApi = (queryParams: object) => {
  let id =
    localStorage.getItem("buildingId") === "0"
      ? null
      : localStorage.getItem("buildingId");

  if (!id) {
    return request("/dormitory/list", "post", {
      ...queryParams
    });
  } else {
    return request("/dormitory/list", "post", {
      ...queryParams,
      buildingId: id
    });
  }
};

export const insertStudentApi = (buildingInfo: object) => {
  return request("/dormitory/insertDormitory", "post", {
    ...buildingInfo
  });
};

export const editStudentApi = (buildingInfo: object) => {
  return request("/dormitory/editDormitory", "post", {
    ...buildingInfo
  });
};

export const deleteStudentApi = (id: number) => {
  return request("/dormitory/deleteDormitory", "get", {
    id
  });
};
