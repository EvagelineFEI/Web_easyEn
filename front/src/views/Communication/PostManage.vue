<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue"
import communicate from "@/api/communication";
import {Ref} from "vue";
import type {UserPostData, PostPages} from "@/api/communication";
import {useAuthStore} from "@/configs/stores/authStore";

const startDate = ref(null as string | null);
const endDate = ref(null as string | null);

const headers = [
  {title: 'Title', align: 'center', key: 'postTitle'},
  {title: 'Date', align: 'end', key: 'postDate'},
  {title: 'Date-line', align: 'end', key: 'prcDuration'},
  {title: '', align: 'end', sortable: false},
];

const auth = useAuthStore();
const recvPosts: Ref<UserPostData[]> = ref([]);


async function loadItems() {
  await communicate.showPostByUser(Number(auth.user_id), {page: 1, pageSize: 10})
    .then((response) => {
      if (response.code === 200) {
        recvPosts.value = response.resultData;
      }
  })
}

// 使用 computed 定义计算属性
const filteredItems = computed(() => {
  return recvPosts.value.filter(post => {
    return (!startDate.value || new Date(post.time as string) >= new Date(startDate.value)) &&
    (!endDate.value || new Date(post.time as string) <= new Date(endDate.value));
  })
});

</script>


<template>
  <v-row>
    <!-- 数据表 -->
    <v-col>
      <v-btn icon @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-row justify="center">
        <v-col cols="10">
          <DataRangeCard
              v-model:start_date="startDate"
              v-model:end_date="endDate">
          </DataRangeCard>
        </v-col>
      </v-row>
      <v-data-table
          :items="filteredItems"
          :headers="headers"
          item-value="postId">
        <template v-slot:item="{ item }">
          <tr>
            <td class="text-center">{{ item.postDate }}</td>
            <td class="text-end">{{ item.postDate }}</td>
            <td class="text-end">{{ item.postDate }}</td>
            <td>
            <v-row justify="end">
                <v-col cols="2"> 
                    <v-btn color="primary" :to="{ name: 'Write', params: { id: item.postId } }">编辑</v-btn>

                </v-col>
                <v-col cols="2"> 
                    <v-btn color="primary" :to="{ name: 'Post', params: { id: item.postId } }">查看</v-btn>
                </v-col>
                <v-col cols="2"> 
                    <v-btn color="error" :to="{ name: 'Post', params: { id: item.postId } }">删除</v-btn>
                </v-col>
            </v-row>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-col>
  </v-row>
</template>


<style scoped>

</style>