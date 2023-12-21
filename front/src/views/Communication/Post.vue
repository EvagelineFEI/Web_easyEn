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
        <v-row v-for="(comment, index) in user_comment" :key="index">
          <v-col cols="12">
            <h3>{{ comment.contents }}</h3>
            <p>{{ comment.comment_time }}</p>
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
import { useRoute } from 'vue-router';
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
  {
    contents: '',
    comment_time:''
  },
]);
const user_comment = ref<CommentData>();
const user_post = ref<UserPostData>();
const newComment = ref({
  author: '',
  message: '',
});
const route = useRoute();
const authStore = useAuthStore();
const postId = computed(() => {
  const rawPostId = route.params.id;
  return typeof rawPostId === 'string' ? parseInt(rawPostId, 10) : 0;
});
onMounted(async () => {    //显示帖子 和 它下面的评论
  try {
    loading.value = true;
    await communicate.showPostComment(postId.value)
      .then((response) => {
        if (response.code === 200) {
          const data = JSON.parse(response.resultData);
          user_comment.value = data.comments;  // 存评论
          user_post.value = data.posts;  // 存帖子
        } else {
          user_comment.value = {} as CommentData;
        }
        console.log(response)
        console.log(user_comment)
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
  // newComment.value.message = '';

  const data: CommentData = {
    //post_id咋传
    post_id: postId.value,
    contents: newComment.value.message

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