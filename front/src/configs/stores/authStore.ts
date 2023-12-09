import {defineStore} from "pinia";

interface Profile {
    id: string;
    name: string;
    email: string;
    avatar: string;
}

export const useAuthStore = defineStore("auth", {
    state: () => ({
        isLoggedIn: false,
        user: null, // cookie with user
        profile: null as Profile | null
    }),

    persist: {
        enabled: true,
        strategies: [
            {storage: localStorage, paths: ["isLoggedIn"]},
        ],
    },

    getters: {},

    actions: {
        loggedIn(JWT: string, userId: string) {
            this.isLoggedIn = true;
            this.user = JWT;
            this.profile = {
                id: userId
            };
        },

        logout() {
            this.user = null;
            this.isLoggedIn = false;
        },
    },
});
