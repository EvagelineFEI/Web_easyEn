<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue";
import practice from "@/api/practice";
import { Ref } from "vue";
import type { WriteData } from "@/api/practice";
import { useAuthStore } from "@/configs/stores/authStore";
import Passages from "@/components/Passages.vue";

const startDate = ref(null as string | null);
const endDate = ref(null as string | null);

const headers = [
  { title: "ID", align: "start", key: "essay_id" },
  { title: "Title", align: "center", key: "essay_title" },
  { title: "Date", align: "center", key: "upload_time" },
  { title: "", align: "end", sortable: false },
];

const auth = useAuthStore();
const userWrites: Ref<WriteData[]> = ref([]);

const snackProvider = ref(false);
const snackProviderMessages = ref("");
const snackColor = ref("success");

const snackHandler = (message: string, color: string) => {
  snackProviderMessages.value = message;
  snackColor.value = color;
  snackProvider.value = true;
};

async function loadItems() {
  await practice.searchWrite(auth.user as string).then((response) => {
    console.log(response);
    if (response.code === 200) {
      userWrites.value = response.resultData;
    } else {
      snackHandler("加载失败", "error");
    }
  });
}

const Delete = async () => {};

const judgeCorrection = async (id: number) => {
  await practice.searchWrite(auth.user as string).then((response) => {
    console.log(response);
    if (response.code === 200) {
      userWrites.value = response.resultData;
    } else {
      snackHandler("加载失败", "error");
    }
  });
};

// 使用 computed 定义计算属性
const filteredItems = computed(() => {
  return userWrites.value.filter((post) => {
    return (
      (!startDate.value ||
        new Date(post.upload_date as string) >= new Date(startDate.value)) &&
      (!endDate.value ||
        new Date(post.upload_date as string) <= new Date(endDate.value))
    );
  });
});

loadItems();
</script>

<template>
  <v-row>
    <!-- 数据表 -->
    <v-col>
      <v-btn icon @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <br />
      <br />
      <v-row justify="center">
        <v-col cols="10">
          <DataRangeCard
            v-model:start_date="startDate"
            v-model:end_date="endDate"
          >
          </DataRangeCard>
        </v-col>
        <v-col cols="1">
          <v-btn @click="loadItems" icon="mdi-refresh" color="primary" />
        </v-col>
      </v-row>
      <v-data-table
        :items="filteredItems"
        :headers="headers"
        item-value="essay_id"
      >
        <template v-slot:item="{ item }">
          <tr>
            <td class="text-center">{{ item.essay_id }}</td>
            <td class="text-center">{{ item.essay_title }}</td>
            <td class="text-end">{{ item.upload_date }}</td>
            <td>
              <v-row justify="space-around">
                <v-col cols="2">
                  <Passages
                    :title="item.essay_title"
                    :content="item.essay_content"
                  />
                </v-col>
                <v-col cols="2">
                  <v-btn
                    variant="text"
                    color="primary"
                    @click="judgeCorrection(Number(item.essay_id))"
                  >
                    批改
                  </v-btn>
                </v-col>
                <v-col cols="2"> </v-col>
              </v-row>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-col>
  </v-row>

  <v-snackbar v-model="snackProvider" :timeout="2000" :color="snackColor">
    {{ snackProviderMessages }}
    <template v-slot:actions>
      <v-btn variant="text" @click="snackProvider = false"> 关闭 </v-btn>
    </template>
  </v-snackbar>
</template>

<style scoped></style>
