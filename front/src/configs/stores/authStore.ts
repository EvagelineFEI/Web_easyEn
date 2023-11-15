import { defineStore } from "pinia";

import index from "@/router";

interface Profile {
  id: string;
  name: string;
  email: string;
  avatar: string;
  created: boolean;
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
      { storage: localStorage, paths: ["isLoggedIn"] },
    ],
  },

  getters: {},

  actions: {
    setLoggedIn(payload:boolean) {
      this.isLoggedIn = payload;
    },

    registerWithEmailAndPassword(email: string, password: string) {
      index.push("/");
    },

    async loginWithEmailAndPassword(email: string, password: string) {
      this.setLoggedIn(true)
      await index.push("/");
    },

    logout() {
      this.setLoggedIn(false)
      index.push({ name: "SignIn" });
    },
  },
});
