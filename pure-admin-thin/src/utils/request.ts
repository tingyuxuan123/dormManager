//1.创建一个新的axios实例
//2. 请求拦截器，如果有token进行头部携带
//3. 响应拦截器：1.剥离无效数据 2. 处理token失效
//4. 导出一个函数，调用当前的axios实例发请求

import axios from "axios";
// import {useUserStore} from "@/stores/userStore"
// import {usescrollStore} from '@/stores/useScroll'
import router from "@/router";
import { ElMessage } from "element-plus";
export const baseURL: string = "http://127.0.0.1:8080/";

const instance = axios.create({
  baseURL,
  timeout: 5000
});

// instance.interceptors.request.use((config)=>{
//     const userStore = useUserStore()
//       const token= userStore.token

//       if(token){
//         config.headers.token=token
//       }

//       return config

// },(err)=>{
//     return Promise.reject(err)
// })

// instance.interceptors.response.use((res)=>{
//     const userStore = useUserStore()
//     if(res.data.code === 401){
//       let scrollStore = usescrollStore()
//         userStore.$reset(); //清空用户数据

//         ElMessage({
//             message: res.data.msg,
//             type: 'warning',
//           })
//      scrollStore.isVisibleLoginForm=true;  //跳出登录界面
//     }

//     return res.data
// },err=>{
//     const userStore = useUserStore()
//     //401 状态码，进入改函数
//     if(err.response && err.response.status === 401){
//         //1.清空无效用户信息
//         //2.跳转到登录页
//         //3.跳转需要传参（当前地址）给登录页码

//         userStore.$reset(); //清空用户数据
//         //js模块中：router.currentRoute.value.fullPath
//         const fullPath:string=encodeURIComponent(router.currentRoute.value.fullPath);
//         //encodeURIComponent 转换url编码，防止解析时出现问题
//         router.push("/login?redirectUrl="+fullPath)

//     }

//     return Promise.reject(err)
// })

export default (
  url: string,
  method: string,
  submitData?: Record<string, any>,
  headers?: Record<string, any>
) => {
  return instance({
    url,
    method,
    //如果是get请求 需要使用params传递，反之使用data
    [method.toLowerCase() === "get" ? "params" : "data"]: submitData,
    headers
  });
};
