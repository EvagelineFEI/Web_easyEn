<script setup lang="ts">
import DateRangeCard from "@/components/DateRangeCard.vue";
import {Ref} from "vue";
import {useAuthStore} from "@/configs/stores/authStore";
import communicate from "@/api/communication";
import type {UserPostData} from "@/api/communication";


const recvPosts: Ref<UserPostData[]> = ref([]);

async function loadItems() {

  await communicate.getAllPost()
    .then((response) => {
      if (response.code === 200) {
        console.log(response)
        recvPosts.value = response.resultData as UserPostData[];
      } else {
        recvPosts.value = [] as UserPostData[];
      }
    })
}

const filteredPosts = computed(() => {
  // 实现关键词和时间检索
  return recvPosts.value.filter(post => {
    return (!startDate.value || new Date(post.time as string) >= new Date(startDate.value)) &&
        (!endDate.value || new Date(post.time as string) <= new Date(endDate.value));
  })
})

const startDate = ref('');
const endDate = ref('');
const triggerKeyword = ref('');
const page = ref(1);

const auth = useAuthStore();

function clearKeyword() {
  triggerKeyword.value = ''
}

loadItems();
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
          @keyup.enter="filteredPosts"
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
              v-for="(item, index) in items"
              :key="index"
          >
            <v-card>
              <v-card-title>{{ item.raw.title }}</v-card-title>
              <v-card-subtitle>{{ item.raw.time }}</v-card-subtitle>
              <v-card-text>{{ item.raw.content }}</v-card-text>
            <div class="text-end pa-4">
              <v-btn color="primary" :to="{ name: 'Post', params: { id: item.raw.post_id } }">详情</v-btn>
            </div>
            </v-card>
            <br/>
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

        <v-card-text v-if="auth.isLoggedIn">
          <v-btn block prepend-icon="mdi-pen" color="primary" :to="{name: 'Write'}">{{ $t("communication.post") }}</v-btn>
          <br/>
          <v-btn block prepend-icon="mdi-note-multiple" color="primary" :to="{name: 'PostManage', params: {userid: 0}}">{{ $t("communication.manage") }}</v-btn>
        </v-card-text>

        <v-card-text v-else>
           请登录后使用
        </v-card-text>
      </v-card>

      <br/>

      <!--热门帖子-->
      <v-card
          prepend-icon="mdi-post"
          :title="$t('communication.populerPost')"
          height="300px">
        <v-container>
          <v-infinite-scroll :height="300" :items="recvPosts">
            <template
                v-for="(item, index) in recvPosts"
                :key="index + 1"
            >
              <v-row justify="start">
                <v-col cols="10">
                  <router-link :to="{name:'Post', params: {id: 0}}" >
                    {{ item.title }}
                  </router-link>
                </v-col>
              </v-row>
              <!--<v-divider/>-->
            </template>
          </v-infinite-scroll>
        </v-container>
      </v-card>
    </v-col>
  </v-row>
</template>