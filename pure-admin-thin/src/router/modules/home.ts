import type { RouteConfigsTable } from "/#/index";
const Layout = () => import("@/layout/index.vue");

const homeRouter: RouteConfigsTable = {
  path: "/",
  name: "Home",
  component: Layout,
  redirect: "/student",
  meta: {
    title: "",
    rank: 0
  },
  children: [
    {
      path: "/student",
      name: "studentPage",
      component: () => import("@/views/student/index.vue"),
      meta: {
        title: "学生管理"
      }
    }
  ]
};
/**
 *   children: [
    {
      path: "/welcome",
      name: "Welcome",
      component: () => import("@/views/welcome/index.vue"),
      meta: {
        title: "首页"
      }
    }
  ]
 * */
export default homeRouter;
