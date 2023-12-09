<template>
    <v-col align="self-center">
        <v-text-field 
            v-model="essay_title" 
            label="标题" 
            variant="outlined"
        />
        <v-row justify="end">
            <v-col cols="2" v-for="item in chekcBoxItems">
                <v-checkbox 
                    :v-model="item.key" 
                    color="primary" 
                    :label="item.title"
                />
            </v-col>
        </v-row>
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
    </v-col>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const essay_title = ref('');
const essay_content = ref('');
const checkPredicate = ref(false);
const checkPro = ref(false);

interface CheckBoxItemType {
    title: string;
    key: any;
}

const chekcBoxItems: CheckBoxItemType[] = [
    {title: '检查谓词', key: checkPredicate},
    {title: '检查拼写', key: checkPro},
];

const submitEssay = () => {
    // 处理提交作文的逻辑
};
</script>

<style scoped>
</style>