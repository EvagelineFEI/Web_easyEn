<script setup lang="ts">
import router from "@/router";
import communicate, {UserPostData} from "@/api/communication";
import {useAuthStore} from "@/configs/stores/authStore";
import {ref} from "vue";

const title = ref('');
const content = ref('');
const isPublic = ref(false);
const auth = useAuthStore();
const snackProvider = ref(false);
const snackColor = ref('success');
const snackProviderMessages = ref('');
const snackHandle = (message: string, color: string) => {
  snackProviderMessages.value = message;
  snackColor.value = color;
  snackProvider.value = true;
}
const publishPost = async () => {
  const data: UserPostData = {
    title: title.value,
    content:content.value,
    public_or:isPublic.value
  }
  await communicate.addPost(auth.user as string,data)
      .then((response) => {
        if (response.code === 200) {
          snackHandle('提交成功', 'success')
          title.value = '';
          content.value = '';
          isPublic.value = false;
        } else {
          snackHandle(response.msg as string, 'error')
        }
      }).catch((error) => {
        snackHandle(error.toString(), 'error')
      })

}
</script>

<template>
    <v-container>
        <v-btn icon @click="$router.go(-1)">
            <v-icon>mdi-arrow-left</v-icon>
        </v-btn>
        <h1>写帖子</h1>
        <v-text-field v-model="title" label="标题" placeholder="请输入标题"></v-text-field>
        <v-textarea auto-grow rows="12" v-model="content" label="内容" placeholder="请输入内容"></v-textarea>
        <v-checkbox v-model="isPublic" label="是否公开"></v-checkbox>
<!--        <v-btn @click="publishPost" color="primary">发布</v-btn>-->
        <v-row justify="end">
          <v-col cols="0">
            <v-btn
                color="primary"
                @click="publishPost"
            >
              提交
            </v-btn>
          </v-col>
        </v-row>
    </v-container>
</template>