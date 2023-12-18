import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";

import auth from "@/router/auth";
import learning from "@/router/learn";
import Track from "@/router/track";
import communication from "@/router/communication";
import {useAuthStore} from "@/configs/stores/authStore";

// register new route in here
const routes = [
  // 首页
  {
    path: "/",
    name: "Home",
    component: Home,
    meta: {
      layout: "default",
    },
  },

  // 交流专区
  {
    path: "/communication",
    meta: {
      layout: "communicate",
    },
    children: communication,
  },

  // 学习专区
  {
    path: "/practice",
    meta: {
      layout: "learn",
      needAuth: true,
    },
    children: learning,
  },

  // 学情交流
  {
    path: "/learn-track",
    meta: {
      layout: "default",
      needAuth: true,
    },
    children: Track,
  },

  // 用户相关
  {
    path: "/auth",
    meta: {
      layout: "default",
    },
    redirect: "/auth/signin",
    children: auth,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from) => {
  if (
    !to.matched.some((record) => record.meta.layout) &&
    from.matched.some((record) => record.meta.layout)
  ) {
    to.meta.layout = from.meta.layout;
  }
  if (
      !to.matched.some((record) => record.meta.needAuth) &&
      from.matched.some((record) => record.meta.needAuth)
  ) {
    to.meta.needAuth = from.meta.needAuth;
  }

  const auth = useAuthStore();
  if (to.meta.needAuth && !auth.isLoggedIn) {
     router.push("/auth/signin");
  }
});

export default router;
