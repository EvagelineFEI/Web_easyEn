<template>
  <v-col>
    <v-col>
      <v-btn icon @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
    </v-col>

    <v-card :loading="loading">
      <v-card-title>
        <h2>{{ postTitle }}</h2>
      </v-card-title>
      <v-card-text min-height="250px">
        <p>Author: John Doe</p>
        <p>Published: 2021-01-01</p>
        <p>{{ postContent }}</p>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="primary" icon>
          <v-icon>mdi-thumb-up</v-icon>
        </v-btn>
        <v-btn color="primary" icon>
          <v-icon>mdi-thumb-down</v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
    <br/>
    <v-card :loading="loading">
      <v-card-title>
        <h2>Comments</h2>
      </v-card-title>
      <v-card-text>
        <v-row v-for="(comment, index) in comments" :key="index">
          <v-col cols="12">
            <h3>{{ comment.author }}</h3>
            <p>{{ comment.message }}</p>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <br/>
    <v-card>
      <v-card-title>
        <h2>发表评论</h2>
      </v-card-title>
      <v-card-text>
        <v-textarea v-model="newComment.message" label="Message"></v-textarea>
        <v-btn color="primary" @click="addComment">发布</v-btn>
      </v-card-text>
    </v-card>
  </v-col>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import type {CommentData, UserPostData} from '@/api/communication';
import communicate from '@/api/communication';
import {useAuthStore} from "@/configs/stores/authStore";

const postTitle = ref('Post Title');
const postContent = ref('Post Content');
const loading = ref(false);
const comments = ref([
  {
    author: 'John Doe',
    message: 'This is a comment.',
  },
  {
    author: 'Jane Smith',
    message: 'Another comment.',
  },
]);
const user_posts = ref<UserPostData>();
const newComment = ref({
  author: '',
  message: '',
});

const authStore = useAuthStore();

onMounted(async () => {
  try {
    loading.value = true;
    await communicate.getAllPost().then((response) => {
      if (response.code === 200) {
        user_posts.value = response.resultData;
      } else {
        user_posts.value = {} as UserPostData;
      }
    })
  } catch (error) {
    console.error("Error fetching posts:", error);
  } finally {
    loading.value = false;
  }
});

async function addComment() {
  comments.value.push({
    author: newComment.value.author,
    message: newComment.value.message,
  });
  newComment.value.author = '';
  newComment.value.message = '';

  const data: CommentData = {
    //post_id咋传
    post_id: 0,
    contents: newComment.message, // 你看看这里要怎么解决
    comment_time: new Date().toDateString(),
  }

  await communicate.postComment(authStore.user as string, data)
      .then((response) => {
        // 处理响应，例如将新评论添加到显示列表
        if (response.code === 200) {
          comments.value.push(response.resultData);
          newComment.value.author = '';
          newComment.value.message = '';
        }
      })
      .catch((error) => {
        console.error("Error posting comment:", error);
      });
}
</script>