import request from "@/utils/request";

export const LoginApi = (form: any) => {
  return request("/user/login", "post", {
    user_NAME: form.username,
    password: form.password
  });
};
