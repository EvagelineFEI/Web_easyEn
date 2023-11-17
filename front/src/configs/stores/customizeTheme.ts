import {defineStore} from "pinia";

interface Color {
    colorId: number;
    colorName: string;
    colorValue: string;
}

interface State {
    miniSidebar: boolean;
    darkTheme: boolean;
    primaryColor: Color;
    mainSidebar: boolean;
    localCode: string;
}

export const useCustomizeThemeStore = defineStore("customizeTheme", {
    state: (): State => ({
        miniSidebar: false,
        darkTheme: false,
        primaryColor: {
            colorId: 4,
            colorName: "success",
            colorValue: "#3db4a0",
        },
        localCode: "en",
        mainSidebar: true,
    }),
    persist: {
        enabled: true,
        strategies: [
            {
                storage: localStorage,
                paths: ["darkTheme", "primaryColor", "localCode", "mainSidebar"],
            },
        ],
    },
    getters: {},
    actions: {
        setMiniSideBar(payload: boolean) {
            this.miniSidebar = payload;
        },
        setPrimaryColor(colors: Color) {
            this.primaryColor = colors;
        },
        setLocalCode(localCode: string) {
            this.localCode = localCode;
        },
    },
});
