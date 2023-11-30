<script setup lang="ts">
import DateRangeCard from "@/components/DateRangeCard.vue";
import {Ref} from "vue";

const posts: Ref<Post[]> = ref([
  {id: 1, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
  {id: 2, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
  {id: 3, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
  {id: 4, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
  {id: 5, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
  {id: 6, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
  {id: 7, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
  {id: 8, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01"},
])

interface Post {
  id: number,
  title: string,
  content: string,
  publishedAt: string
}

const filteredPosts = computed(() => {
  // 实现关键词和时间检索
  return posts.value.filter(post => {
    return (!startDate.value || new Date(post.publishedAt) >= new Date(startDate.value)) &&
        (!endDate.value || new Date(post.publishedAt) <= new Date(endDate.value));
  })
})

const startDate = ref('');
const endDate = ref('');
const triggerKeyword = ref('');
const page = ref(1);

function clearKeyword() {
  triggerKeyword.value = ''
}
</script>

<template>
  <v-row class="d-flex justify-space-between mb-auto">
    <!-- 数据表 -->
    <v-col class="justify-space-between ms-md-auto" cols="9">
      <v-text-field
          v-model="triggerKeyword"
          prepend-inner-icon="mdi-magnify"
          density="compact"
          variant="solo"
          label="搜素关键词"
          single-line
          hide-details
          clearable
          clear-icon="mdi-close-circle"
          @click:clear="clearKeyword"
      ></v-text-field>

      <br/>

      <DateRangeCard
          v-model:start_date="startDate"
          v-model:end_date="endDate">
      </DateRangeCard>

      <br/>

      <v-data-iterator :items="filteredPosts" :page="page" :items-per-page="5" :search="triggerKeyword">
        <template v-slot:default="{ items }">
          <template
              v-for="(item, i) in items"
              :key="i"
          >
            <v-card>
              <v-card-title>{{ item.raw.title }}</v-card-title>
              <v-card-subtitle>{{ item.raw.publishedAt }}</v-card-subtitle>
              <v-card-text>{{ item.raw.content }}</v-card-text>
            <div class="text-end pa-4">
              <v-btn color="primary" :to="{ name: 'Post', params: { id: item.raw.id } }">详情</v-btn>
            </div>
            </v-card>
            <br>
          </template>
        </template>

        <template v-slot:footer="{ page, pageCount, prevPage, nextPage }">
          <div class="d-flex align-center justify-end pa-4">
            <v-btn
              :disabled="page === 1"
              icon="mdi-arrow-left"
              density="comfortable"
              variant="tonal"
              rounded
              @click="prevPage"
            ></v-btn>

            <div class="mx-2 text-caption">
              Page {{ page }} of {{ pageCount }}
            </div>

            <v-btn
              :disabled="page >= pageCount"
              icon="mdi-arrow-right"
              density="comfortable"
              variant="tonal"
              rounded
              @click="nextPage"
            ></v-btn>
          </div>
        </template>
      </v-data-iterator>
    </v-col>

    <v-col class="justify-space-between ms-md-auto" cols="3">

      <!--工具箱-->
      <v-card
          prepend-icon="mdi-toolbox"
          :title="$t('communication.toolbox')">
        <v-card-text>
          <v-btn block prepend-icon="mdi-pen" color="primary">{{ $t("communication.post") }}</v-btn>
          <br/>
          <v-btn block prepend-icon="mdi-note-multiple" color="primary">{{ $t("communication.manage") }}</v-btn>
        </v-card-text>
      </v-card>

      <br/>

      <!--热门帖子-->
      <v-card
          prepend-icon="mdi-toolbox"
          :title="$t('communication.purpulerPost')">
        <v-data-iterator>

        </v-data-iterator>
        <v-card-text>
          
        </v-card-text>
      </v-card>
    </v-col>

  </v-row>
</template>