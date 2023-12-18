<template>
  <div class="home-page">
    <!-- Logo 和 网站介绍 -->
    <div class="header">
      <v-img :src="getLogo()" alt="Logo" class="logo"/>
      <p>Welcome to our English Learning Website!</p>
    </div>
    <!-- 四个浮动框组件 -->
    <div class="features">
      <v-row>
        <v-col cols="12" sm="6" md="3" v-for="feature in features" :key="feature.title">
          <v-card hover class="feature-card">
            <v-card-title>{{ feature.title }}</v-card-title>
            <v-card-text>{{ feature.description }}</v-card-text>
            <v-img :src="getUnitLogo(feature.image)" alt="Feature Image" class="feature-image"/>
          </v-card>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script setup lang="ts">
const features = [
  { title: 'Feature 1', description: '在这里与其他英语学习爱好者一起成长！~交流你们的学习收获，体验和宝贵的经验！', image:'../assets/EAZYEN-LOGO.png'},
  { title: 'Feature 2', description: '集成了较多的英语学习功能~背单词，阅读，作文批改',image:'../assets/EAZYEN-LOGO.png' },
  { title: 'Feature 3', description: '你可以在可视化部分查看自己的学习进度~帮助自己反思，总结每日的成长目标', image:'../assets/EAZYEN-LOGO.png' },
  { title: 'Feature 4', description: '你可以在可视化部分查看自己的学习进度~帮助自己反思，总结每日的成长目标', image:'../assets/EAZYEN-LOGO.png' },
];

// 从 PUBLIC 目录中获取 Logo
const getLogo = () => {
  return new URL('~/public/EAZYEN-LOGO.png', import.meta.url).href
}

function getUnitLogo(src: string) {
  return new URL(src, import.meta.url).href
}
</script>

<style lang="scss" scoped>
.home-page {
  text-align: center;
  /* 使用一个接近薄荷绿的颜色创建一个全屏渐变背景 */
  //background: linear-gradient(to bottom, #88D8B0, #BFFFD6); /* 淡薄荷绿渐变 */
  min-height: 70vh; /* 确保背景覆盖整个视口高度 */
  margin: 0; /* 移除外边距 */
  padding: 0; /* 移除内边距 */
}

.header {
  margin: 40px 0; /* 增加顶部空间 */
}

.logo {
  width: 200px; /* 根据实际情况调整logo宽度 */
  height: auto; /* 保持图片的纵横比 */
  margin: 0 auto; /* 水平居中 */
}

.features .feature-card {
  margin: 20px; /* 增加特性卡片的间距 */
  height: 300px;
  box-shadow: rgba(99, 99, 99, 0.2) 0 4px 16px 0; /* 增强阴影效果 */
  font-size: 50px; /* 增大字体大小 */
  line-height: 3; /* 增大行距 */
  font-family: "YouYuan", "Microsoft YaHei", sans-serif;
  justify-content: center; /* 垂直居中内容 */
  text-align: center;
}
</style>

