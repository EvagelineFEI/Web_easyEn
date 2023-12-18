<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue"
import practice from "@/api/practice";
import {Ref} from "vue";
import type {WriteData} from "@/api/practice";
import {useAuthStore} from "@/configs/stores/authStore";

const startDate = ref(null as string | null);
const endDate = ref(null as string | null);

const headers = [
  {title: 'Title', align: 'center', key: 'postTitle'},
  {title: 'Date', align: 'end', key: 'postDate'},
  {title: '', align: 'end', sortable: false},
];

const auth = useAuthStore();
const userWrites: Ref<WriteData[]> = ref([]);
const showPassage = ref(false);

async function loadItems() {
  await practice.searchWrite(auth.user as string)
    .then((response) => {
      if (response.code === 200) {
        userWrites.value = response.resultData;
      }
  })
}

// 使用 computed 定义计算属性
const filteredItems = computed(() => {
  return userWrites.value.filter(post => {
    return (!startDate.value || new Date(post.upload_date as string) >= new Date(startDate.value)) &&
    (!endDate.value || new Date(post.upload_date as string) <= new Date(endDate.value));
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
            <td class="text-center">{{ item.essay_id }}</td>
            <td class="text-center">{{ item.essay_title }}</td>
            <td class="text-end">{{ item.upload_date }}</td>
            <td>
            <v-row justify="end">
              <v-col cols="2">
                  <v-btn
                      color="primary"
                      @click="showPassage = !showPassage"
                  >
                    查看
                  </v-btn>
              </v-col>
              <v-col cols="2">
                <v-btn color="error">批改</v-btn>
              </v-col>
              <v-col cols="2">
                <v-btn color="error" :to="{ name: 'Post', params: { id: item.postId } }">批改</v-btn>
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

  // show passage
  <v-dialog>

  </v-dialog>
</template>


<style scoped>

</style>