<template>
    <v-col align-self="center">
        <v-text-field 
            v-model="essay_title" 
            label="标题" 
            variant="outlined"
        />
        <v-row justify="end">
            <v-col cols="2" v-for="i in 3">
                <v-checkbox 
                    v-model="checkPredicate" 
                    color="primary" 
                    label="检查谓词"
                />
            </v-col>
        </v-row>
        <v-textarea 
            v-model="essay_content" 
            label="作文内容" 
            variant="outlined" 
            density="comfortable"
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

const submitEssay = () => {
    // 处理提交作文的逻辑
};
</script>

<style scoped>
</style>