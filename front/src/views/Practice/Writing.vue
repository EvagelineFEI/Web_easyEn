<template>
<v-col align="self-center">
    <!--<>-->
    <CenterProgress class="h-full" v-if="status.valueOf() === 'loading'" />
    <div v-else-if="status.valueOf() === 'write'">
        <v-text-field
            v-model="essay_title"
            label="标题"
            variant="outlined"
        />
        <v-text-field
            v-model="essay_req"
            label="要求(比重点如关注语法纠错或者句子结构提升)"
            variant="outlined"
        />

        <v-textarea
            v-model="essay_content"
            label="作文内容"
            variant="outlined"
            density="comfortable"
            rows="18"
        />
        <v-row justify="end">
            <v-col cols="2">
                <v-btn
                color="primary"
                @click="submitEssay"
                >
                    提交
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


<script setup lang="ts">
import { ref } from 'vue';
import practice from "@/api/practice";
import type { WriteData } from "@/api/practice";
import CenterProgress from "@/components/CenterProgress.vue";
import Answer from "@/components/Answer.vue";
import {useAuthStore} from "@/configs/stores/authStore";

const essay_title = ref('');
const essay_req = ref('');
const essay_content = ref('');

const snackProvider = ref(false);
const snackColor = ref('success');
const snackProviderMessages = ref('');
const status = ref("write")

interface CheckBoxItemType {
    title: string;
    key: any;
}

const authStore = useAuthStore();

async function submitEssay() {
    const data: WriteData = {
        essay_title: essay_title.value,
        essay_requirements: essay_req.value,
        essay_content: essay_content.value,
    }

    await practice.submitEssay(authStore.user as string, data)
        .then((response) => {
            if (response.code === 200) {
                snackHandle('提交成功', 'success')
                essay_title.value = '';
                essay_content.value = '';
                essay_req.value = '';
            } else {
                snackHandle(response.msg as string, 'error')
            }
        }).catch((error) => {
            snackHandle(error.toString(), 'error')
        })
}

const snackHandle = (message: string, color: string) => {
    snackProviderMessages.value = message;
    snackColor.value = color;
    snackProvider.value = true;
}
</script>