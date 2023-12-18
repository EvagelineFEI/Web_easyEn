import {requester} from "@/api/utils";

interface AuthStruct {
    username?: string,
    user_id?: number,
    password?: string,
    email?: string,
    code?: string,
    other_info?: string,
    token?: string
}

const auth = {
    login: async (params: AuthStruct) =>
    requester<AuthStruct>({
        url: "/login",
        method: "post",
        params
    }),

    register: async (params: AuthStruct) =>
    requester<AuthStruct>({
        url: "/register",
        method: "post",
        params
    }),

    getUserInfo: async (token: string, data: AuthStruct) =>
    requester<AuthStruct>({
        url: "/user/QueryUser",
        method: "post",
        headers: {
            Authorization: token
        },
        data,
    }),

    updateUserInfo: async (token: string, data: AuthStruct) =>
    requester<AuthStruct>({
        url: "/user/QueryUser",
        method: "post",
        headers: {
            Authorization: token
        },
        data,
    }),
}

export default auth;

export type {
    AuthStruct,
}