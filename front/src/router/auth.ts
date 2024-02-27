import SignIn from "@/views/Auth/SignIn";
import SignUp from "@/views/Auth/SignUp";
import ForgotPasswd from "@/views/Auth/ForgotPasswd";

export default [
    {
        path: 'signin',
        name: 'SignIn',
        component: SignIn,
        meta: {
            layout: "default"
        }
    },
    {
        path: 'profile/:user',
        name: 'Profile',
        component: SignIn,
        meta: {
            layout: "default"
        }
    },
    {
        path: 'signup',
        name: 'SignUp',
        component: SignUp,
        meta: {
            layout: "default"
        }
    },
    {
        path: 'forgot-password',
        name: 'Forgot',
        component: ForgotPasswd,
        meta: {
            layout: "default"
        }
    },
]