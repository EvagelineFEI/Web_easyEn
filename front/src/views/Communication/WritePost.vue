<template>
    <v-container>
        <v-btn icon @click="$router.go(-1)">
            <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <h1>写帖子</h1>
        <v-text-field v-model="title" label="标题" placeholder="请输入标题"></v-text-field>
        <v-textarea auto-grow rows="12" v-model="content" label="内容" placeholder="请输入内容"></v-textarea>
        <v-btn @click="publishPost" color="primary">发布</v-btn>
    </v-container>
</template>

<script>
export default {
    data() {
        return {
            title: '',
            content: ''
        };
    },
    methods: {
        publishPost() {
            // 发布帖子的逻辑
        },
        goBack() {
            // 返回上一级的逻辑
        }
    }
};
</script>

<style scoped>
</style>
