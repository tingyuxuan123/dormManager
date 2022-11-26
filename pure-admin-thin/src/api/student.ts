import request from "@/utils/request";

export const studentListApi = (queryParams: object) => {
  let id =
    localStorage.getItem("buildingId") === "0"
      ? null
      : localStorage.getItem("buildingId");
  if (!id) {
    return request("/student/list", "post", {
      ...queryParams
    });
  } else {
    return request("/student/list", "post", {
      ...queryParams,
      buildingId: id
    });
  }
};

export const insertStudentApi = (studentInfo: object) => {
  return request("/student/insertStudent", "post", {
    ...studentInfo
  });
};

export const editStudentApi = (studentInfo: object) => {
  return request("/student/editStudent", "post", {
    ...studentInfo
  });
};

export const deleteStudentApi = (id: number) => {
  return request("/student/deleteStudent", "get", {
    id
  });
};
