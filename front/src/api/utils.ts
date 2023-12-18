import axios, {AxiosRequestConfig} from "axios";

interface Response<T = any> {
    code: number,
    msg: string,
    resultData: T
}

const request = axios.create({
    baseURL: '/api',
})


export type {
    Response
}

export async function requester<T>(config: AxiosRequestConfig) {
    return request.request<Response<T>>(config)
        .then((res) => {
            return res.data;
        }).catch( function (error) {
            return {
                code: 0,
                msg: error.toString(),
                resultData: ""
            } as Response
        })
}