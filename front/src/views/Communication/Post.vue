<template>
  <v-col>
    <v-col>
      <v-btn icon @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
    </v-col>

    <v-card :loading="loading">
      <v-card-title>
        <h2>{{ title }}</h2>
      </v-card-title>
      <v-card-text min-height="250px">
        <p>{{ time }}</p>
        <p> Author: {{ author }}</p>
        <p>{{ content }}</p>
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
        <v-row v-for="(comment, index) in user_comment" :key="index" v-if="user_comment.length !== 0">
          <v-col cols="12">
            <v-divider></v-divider>
            <h3>{{ comment.contents }}</h3>
            <p> User: {{ comment.user_id }}, {{ formatDate(comment.comment_time) }}</p>
            <br/>
          </v-col>
        </v-row>
        <div v-else>
          No Comments
        </div>
      </v-card-text>
    </v-card>

    <br/>

    <v-card>
      <v-card-title>
        <h2>发表评论</h2>
      </v-card-title>
      <v-card-text>
        <v-textarea
            :disabled="!authStore.isLoggedIn"
            v-model="newComment.message"
            :label="authStore.isLoggedIn ? '输入信息' : '此功能仅限登录用户使用'"
        />
        <v-btn
            :disabled="!authStore.isLoggedIn"
            color="primary"
            @click="addComment">
          发布
        </v-btn>
      </v-card-text>
    </v-card>
  </v-col>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue';
import type {CommentData, UserPostData, PostComment} from '@/api/communication';
import communicate from '@/api/communication';
import {useAuthStore} from "@/configs/stores/authStore";
import { useRoute } from 'vue-router';


const loading = ref(false);
const user_comment = ref<CommentData[]>([]);

const title = ref<string>('');
const time = ref<string>('');
const author = ref<number>(0);
const content = ref<string>('');

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
          console.log(response)
          const data: PostComment = response.resultData;
          user_comment.value = data.comments;  // 存评论
          title.value = data.posts.title;
          time.value = formatDate(data.posts.time as string);
          author.value = Number(data.posts.user_id);
          content.value = data.posts.content as string;
        } else {
          user_comment.value = [] as CommentData[];
        }
        console.log(user_comment)
    })
  } catch (error) {
    console.error("Error fetching posts:", error);
  } finally {
    loading.value = false;
  }
});

function formatDate(input: string): string {
  let date = new Date(input);

  const options: Intl.DateTimeFormatOptions = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
  };

  return new Intl.DateTimeFormat('cn-ZH', options).format(date);
}

async function addComment() {

  newComment.value.author = '';
  // newComment.value.message = '';

  const data: CommentData = {
    post_id: postId.value,
    contents: newComment.value.message
  }

  await communicate.postComment(authStore.user as string, data)
      .then((response) => {
        // 处理响应，例如将新评论添加到显示列表
        if (response.code === 200) {
          newComment.value.author = '';
          newComment.value.message = '';
          user_comment.value.push(response.resultData);
        }
      })
      .catch((error) => {
        console.error("Error posting comment:", error);
      });
}
</script>