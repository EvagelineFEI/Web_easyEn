<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue";
import Passages from "@/components/Passages.vue";

import practice from "@/api/practice";
import type {WriteData} from "@/api/practice";

import {useAuthStore} from "@/configs/stores/authStore";

const startDate = ref(null as string | null);
const endDate = ref(null as string | null);

const headers = [
  {title: "ID", align: "start", key: "essay_id"},
  {title: "Title", align: "center", key: "essay_title"},
  {title: "Date", align: "center", key: "upload_time"},
  {title: "Actions", key: "actions", align: "center", sortable: false},
];

const auth = useAuthStore();
const userWrites = ref<WriteData[]>([]);

const snackProvider = ref(false);
const snackProviderMessages = ref("");
const snackColor = ref("success");

const suggestionDialog = ref(false)

const suggestionPassageID = ref<number>(0);
const suggestion = ref('');

const overlay = ref(false);

const snackHandler = (message: string, color: string) => {
  snackProviderMessages.value = message;
  snackColor.value = color;
  snackProvider.value = true;
};

async function loadItems() {
  await practice.searchWrite(auth.user as string)
      .then((response) => {
        console.log(response);
        if (response.code === 200) {
          userWrites.value = response.resultData;
          snackHandler("加载成功", "success");
        } else {
          snackHandler("加载失败", "error");
        }
      });
}

const deleteEssay = async (essay_id: number) => {
  const data: WriteData = {
    essay_id,
    user_id: auth.user_id,
  }
  overlay.value = true;

  await practice.deleteEssay(auth.user as string, data)
      .then((response) => {
        console.log(response);
        if (response.code === 200) {
          snackHandler("删除成功", "success");
          loadItems();
        } else {
          snackHandler("加载失败", "error");
        }
      });

  overlay.value = false;
};

const judgeCorrection = async (essay_id: number) => {
  const data: WriteData = {
    essay_id
  }
  overlay.value = true;

  await practice.essayAns(auth.user as string, data)
      .then((response) => {
        console.log(response);
        if (response.code === 200) {
          console.log(response.resultData);
          suggestionPassageID.value = response.resultData.essay_id;
          suggestion.value = response.resultData.correction_suggestions;
          suggestionDialog.value = true;
          snackHandler("批改成功，稍后查看结果", "success");
        } else {
          snackHandler(response.msg, "error");
        }
      });

  overlay.value = false;
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

function formatDate(input: string): string {
  let date = new Date(input);

  const options: Intl.DateTimeFormatOptions = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
  };

  return new Intl.DateTimeFormat('cn-ZH', options).format(date);
}

loadItems();
</script>

<template>
  <v-row>
    <!-- 数据表 -->
    <v-col>
      <v-btn icon @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <br/>
      <br/>
      <v-row justify="center">
        <v-col cols="10">
          <DataRangeCard
              v-model:start_date="startDate"
              v-model:end_date="endDate"
          />
        </v-col>
        <v-col cols="1">
          <v-btn @click="loadItems" icon="mdi-refresh" color="primary"/>
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
            <td class="text-end">{{ formatDate(item.upload_date) }}</td>
            <td>
              <v-row justify="space-around">
                <v-col cols="4">
                  <Passages
                      :title="item.essay_title"
                      :content="item.essay_content"
                  />
                </v-col>
                <v-col cols="4">
                  <v-btn
                      variant="text"
                      icon="mdi-pen"
                      color="primary"
                      @click="judgeCorrection(Number(item.essay_id))"
                  />
                </v-col>
                <v-col cols="4">
                  <v-dialog
                      max-width="500px"
                  >
                    <template v-slot:activator="{ props }">
                      <v-btn
                          variant="text"
                          icon="mdi-delete"
                          color="error"
                          v-bind="props"
                      />
                    </template>
                    <v-card>
                      <v-card-title>
                        删除文章
                      </v-card-title>
                      <v-card-subtitle>
                        点击方框外取消
                      </v-card-subtitle>
                      <v-card-text>
                        是否要删除文章？
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer/>
                        <v-btn
                            color="primary"
                            @click="deleteEssay(Number(item.essay_id))"
                        >
                          确定
                        </v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-col>
              </v-row>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-col>
  </v-row>

  <v-dialog
      v-model="suggestionDialog"
      max-width="500px"
  >
    <v-card>
      <v-card-title>
        批改结果
      </v-card-title>
      <v-card-text>
        {{suggestion}}
      </v-card-text>
      <v-card-actions>
        <v-spacer/>
        <v-btn
            color="primary"
            @click="suggestionDialog = !suggestionDialog"
        >
          关闭
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <v-overlay
      persistent
      :model-value="overlay"
      class="align-center justify-center"
  >
    <v-progress-circular
        color="primary"
        indeterminate
        size="64"
    ></v-progress-circular>
  </v-overlay>

  <v-snackbar v-model="snackProvider" :timeout="2000" :color="snackColor">
    {{ snackProviderMessages }}
    <template v-slot:actions>
      <v-btn variant="text" @click="snackProvider = false"> 关闭</v-btn>
    </template>
  </v-snackbar>
</template>

<style scoped></style>
