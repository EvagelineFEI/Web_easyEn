import {json_request} from "@/api/utils";

interface AuthRequest {
    userEmail: string,
    userPasswd?: string
}

type AuthResponsesData =
       LoginResponses | RegisterResponses | null;

interface LoginResponses {
    JWT: string,
    userId: number
}

interface RegisterResponses {
    JWT: string,
    userId: number
}


export class Practice {
    async login(email: string, password: string)  {
        
        const Data: AuthRequest = {
            userEmail: email,
            userPasswd: password
        }

        return await json_request(null ,'/login/Email', Data)
    }
    
    async register(email: string, password: string)  {
        
        const Data: AuthRequest = {
            userEmail: email,
            userPasswd: password
        }
        
        return await json_request(null ,'/register/Email', Data)
    }
    
    async forgot(email: string) {
        const Data: AuthRequest = {
            userEmail: email
        }
        
        return await json_request(null ,'/forgot/Email', Data)
    }
}

export type {
    AuthRequest,
    AuthResponsesData
}