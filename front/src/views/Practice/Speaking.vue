<script setup lang="ts">
import CenterProgress from "@/components/CenterProgress.vue";
import Answer from "@/components/Answer.vue";
import practice from "@/api/practice";
import type {SpeakTopicData} from "@/api/practice";
import {useAuthStore} from "@/configs/stores/authStore";

const snackProvider = ref(false);
const snackProviderMessages = ref('');
const snackColor = ref('success');

const status = ref('write');

const essay_req = ref('');
const essay_content = ref('');
const auth = useAuthStore();

const snackHandler = (message: string, color: string) => {
  snackProviderMessages.value = message;
  snackColor.value = color;
  snackProvider.value = true;
}

const submitTopic = async () => {
  if (essay_req.value === '' || essay_content.value === '') {
    snackHandler('请填写完整信息', 'error');
    return;
  }
  const data: SpeakTopicData = {
    requirements: essay_req.value,
    topic: essay_content.value,
  }

  await practice.getTopicAns(auth.user as string, data)
      .then((response) => {
        if (response.code === 200) {
          snackHandler('提交成功', 'success');
          // status.value = 'answer';
          return JSON.parse(response.resultData);
        } else {
          snackHandler('提交失败', 'error');
        }
      })
}

</script>

<template>
  <v-col align="self-center">
    <!--<>-->
    <CenterProgress class="h-full" v-if="status.valueOf() === 'loading'" />
    <div v-else-if="status.valueOf() === 'write'">
      <v-text-field
          v-model="essay_req"
          label="口语考试类型（比如雅思或者托福）"
          variant="outlined"
      />

      <v-textarea
          v-model="essay_content"
          label="口语问题"
          variant="outlined"
          density="comfortable"
          rows="18"
      />
      <v-row justify="end">
        <v-col cols="2">
          <v-btn
              color="primary"
              @click="submitTopic"
          >
            提交并得到话题回答
          </v-btn>
        </v-col>
      </v-row>
    </div>
    <Answer v-else v-model:status="status"/>
  </v-col>

  <v-snackbar
      v-model="snackProvider"
      :timeout="2000"
      :color="snackColor">
    {{ snackProviderMessages }}
    <template v-slot:actions>
      <v-btn
          variant="text"
          @click="snackProvider = false">
        关闭
      </v-btn>
    </template>
  </v-snackbar>
</template>

<style scoped>

</style>