import {defineStore} from "pinia";

export interface Profile {
    name: string;
    email: string;
    content: string;
}

export const useAuthStore = defineStore("auth", {
    state: () => ({
        isLoggedIn: false,
        user: null as string | null, // cookie with user
        user_id: 0,
        profile: null as Profile | null
    }),

    persist: {
        enabled: true,
    },

    getters: {},

    actions: {
        loggedIn(JWT: string, user_id: number) {
            this.isLoggedIn = true;
            this.user = JWT;
            this.user_id = user_id;
        },

        updateProfile(profile: Profile) {
            this.profile = profile;
        },

        logout() {
            this.user = null;
            this.isLoggedIn = false;
        },
    },
});
