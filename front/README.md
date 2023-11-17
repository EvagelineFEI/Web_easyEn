# 易学英语网

## Toolchains
Use `Vue 3` + `Vite` + `Vuetify`

Base on `Node.js` , version `v20.8.1`

Router components is `vue-router`

Storage components is `Pinia`

## Project Struct

```
├── auto-imports.d.ts
├── tsconfig.js
├── index.html # 站点基本信息和字体依赖
├── package.json # 依赖包
├── package-lock.json
├── public
│   └── EAZYEN-LOGO.png # 站点html title LOGO
├── README.md
├── src
│   ├── App.vue
│   ├── assets # 页面内显示的icon依赖
│   ├── components # 可在各页面能最小化被利用的组件
│   ├── configs # 包括API menu stores 
│   ├── layouts # 视图依赖的布局
│   ├── main.ts 
│   ├── plugins # 外部插件的初始化
│   ├── router # vue-router 声明
│   ├── styles # scss 
│   └── views # 各页面视图
├── tsconfig.json # TypeScript 配置
└── vite.config.ts # vite 插件配置
```