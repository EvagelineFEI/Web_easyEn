<template>
  <v-row>
    <!-- 数据表 -->
    <v-col>
      <v-container
        min-height="70vh"
        elevation="4"
        rounded="lg"
        border
      >
        <v-row>
          <v-col v-for="post in filteredPosts" :key="post.id" cols="12">
            <v-card>
              <v-card-title>{{ post.title }}</v-card-title>
              <v-card-subtitle>{{ post.publishedAt }}</v-card-subtitle>
              <v-card-text>{{ post.content }}</v-card-text>
              <v-card-actions>
                <v-btn :to="{ name: 'Post', params: { id: post.id } }">详情</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-col>
    
    <DateRangeCard
    v-model:start_date="startDate"
    v-model:end_date="endDate">
    </DateRangeCard>

  </v-row>
</template>

<script setup lang="ts">
import DateRangeCard from "@/components/DateRangeCard.vue";

const props = defineProps( {
    posts: {
      type: Array,
      required: true,
      default: () => [
        { id: 1, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01" },
        { id: 2, title: "Vue.js 3.0 发布啦！", content: "Vue 3.0 带来了许多新特性...", publishedAt: "2023-01-01" }
      ]
    },
  })

const filteredPosts = computed(() => {
  // 实现关键词和时间检索
  return props.posts.filter(post => {
    const keywordMatch = post.title.toLowerCase().includes(searchKeyword.value.toLowerCase());
    const dateMatch = (!startDate.value || new Date(post.publishedAt) >= new Date(startDate.value)) &&
                          (!endDate.value || new Date(post.publishedAt) <= new Date(endDate.value));
    return keywordMatch && dateMatch;
  })
})

const startDate = ref(null);
const endDate = ref(null);
const searchKeyword = ref('');
</script>