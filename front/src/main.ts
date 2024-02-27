import { createApp } from 'vue'
import App from "./App"

// Vuetify
import 'vuetify/styles'
import router from "@/router";
import vuetify from "@/plugins/vuetify";
import i18n from "@/plugins/i18n";
import pinia from "@/plugins/pinia";
import {autoAnimatePlugin} from "@formkit/auto-animate/vue";
import "@/styles/main.scss"

const app = createApp(App)

app.use(router)
app.use(vuetify)
app.use(i18n)
app.use(pinia)
app.use(autoAnimatePlugin)
router
    .isReady()
    .then(()=> app.mount('#app'))
    .catch(e => console.error(e));
