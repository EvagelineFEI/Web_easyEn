import { defineConfig } from 'vite'
import vuetify from "vite-plugin-vuetify";
import AutoImport from "unplugin-auto-import/vite";

import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from "node:url";


export default defineConfig({
  server: {
    host: "0.0.0.0"
  },
  plugins: [
    vue(),
    vuetify({
      autoImport: true,
      styles: { configFile: "src/styles/variables.scss" },
    }),
    AutoImport({
      imports: ["vue", "vue-router", "pinia"],
    }),
  ],
  resolve: {
    alias: {
      "~": fileURLToPath(new URL("./", import.meta.url)),
      "@": fileURLToPath(new URL("./src", import.meta.url)),
      "@data": fileURLToPath(new URL("./src/data", import.meta.url)),
    },
    extensions: [".js", ".json", ".jsx", ".mjs", ".ts", ".tsx", ".vue"],
  },
})
