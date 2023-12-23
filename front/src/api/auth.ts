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
        await requester<AuthStruct>({
            url: "/login",
            method: "post",
            params
        }),

    register: async (params: AuthStruct) =>
        await requester<AuthStruct>({
            url: "/register",
            method: "put",
            params
        }),

    getCheckCode: async (params: any) =>
        await requester<string>({
            url: '/getCheckCode',
            method: "POST",
            params
        }),

    newPassword: async (data: AuthStruct, params: any) =>
        await requester<string>({
            url: '/forgetPwd',
            method: "POST",
            data,
            params
        }),

    getUserInfo: async (token: string, data: AuthStruct) =>
        await requester<AuthStruct>({
            url: "/user/QueryUser",
            method: "post",
            headers: {
                Authorization: token
            },
            data,
        }),

    updateUserInfo: async (token: string, data: AuthStruct) =>
        await requester<AuthStruct>({
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