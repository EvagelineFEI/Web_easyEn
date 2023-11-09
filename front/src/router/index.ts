import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/views/Home";
import LearnStatus from "@/views/LearnStatus";
import Learning from "@/views/Practice";
import Communication from "@/views/Communication";
import auth from "@/router/auth";
import learning from "@/router/learn";

// register new route in here
const routes = [
    // 首页
    {
        path: '/',
        name: 'Home',
        component: Home,
        meta: {
            layout: "default"
        }
    },

    // 交流专区
    {
        path: '/communication',
        name: 'Communication',
        component: Communication,
        children: [],
        meta: {
            layout: "communicate"
        }
    },

    // 学习专区
    {
        path: '/practice',
        name: 'Practice',
        component: Learning,
        meta: {
            layout: "learn"
        },
        children: learning
    },

    // 学情交流
    {
        path: '/learn-status',
        name: 'LearnStatus',
        component: LearnStatus,
        children: [],
        meta: {
            layout: "learn"
        }
    },

    // 用户相关
    {
        path: '/auth',
        redirect: '/auth/signin',
        children: auth
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from) => {
    if (!to.matched.some(record => record.meta.layout) && from.matched.some(record => record.meta.layout)) {
        to.meta.layout = from.meta.layout
    }
})

export default router;