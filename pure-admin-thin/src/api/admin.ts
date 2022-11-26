import request from "@/utils/request";

export const studentListApi = (queryParams: object) => {
  return request("/admin/list", "post", {
    ...queryParams
  });
};

export const insertStudentApi = (adminInfo: object) => {
  return request("/admin/insertAdmin", "post", {
    ...adminInfo
  });
};

export const editStudentApi = (adminInfo: object) => {
  return request("/admin/editAdmin", "post", {
    ...adminInfo
  });
};

export const deleteStudentApi = (id: number) => {
  return request("/admin/deleteAdmin", "get", {
    id
  });
};
