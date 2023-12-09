import axios from "axios";
import {AuthRequest, AuthResponsesData} from "@/api/auth";

type JsonRequest = AuthRequest | null;

type ParamRequest = AuthRequest | null;

type ResponsesData = AuthResponsesData | null;

interface Responses {
    code: number,
    data?: ResponsesData
}

const requester = axios.create({
    baseURL: '/api',
})

export async function json_request(JWT: string | null, url: string, Data: JsonRequest) {
    const config = {
        headers: {
            Authorization: JWT
        }
    };
    return await requester.post( url, Data, config )
        .then(({data}:{data: Responses}) => {
            return data
        })
        .catch((error) => {
            return {code: 0,} as Responses
        })
}

export async function params_request(JWT: string | null, url: string, Data: ParamRequest) {
    const config = {
        params: Data,
        headers: {
            Authorization: JWT
        }
    };
    return await requester.get( url, config )
        .then(({data}:{data: Responses}) => {
            return data
        })
        .catch((error) => {
            return {code: 0,} as Responses
        })
}