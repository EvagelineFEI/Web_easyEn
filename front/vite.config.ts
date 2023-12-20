import { defineConfig } from 'vite'
import vuetify from "vite-plugin-vuetify";
import AutoImport from "unplugin-auto-import/vite";

import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from "node:url";


export default defineConfig({
  server: {
    host: "0.0.0.0",
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:6660/api',
        rewrite: (path) => path.replace(/^\/api/, ''),
        changeOrigin: true,
        ws: true,
      },
    },
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
      "@api": fileURLToPath(new URL("./src/api", import.meta.url)),
      "@configs": fileURLToPath(new URL("./src/configs", import.meta.url)),
    },
    extensions: [".js", ".json", ".jsx", ".mjs", ".ts", ".tsx", ".vue"],
  },
})
