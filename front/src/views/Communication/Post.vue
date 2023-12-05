<template>
    <v-col>
        <v-col>
            <v-btn icon @click="$router.go(-1)">
                <v-icon>mdi-arrow-left</v-icon>
            </v-btn>
        </v-col>

        <v-card :loading="loading">
            <v-card-title>
                <h2>{{ postTitle }}</h2>
            </v-card-title>
            <v-card-text  min-height="250px">
                <p>Author: John Doe</p>
                <p>Published: 2021-01-01</p>
                <p>{{ postContent }}</p>
            </v-card-text>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" icon>
                    <v-icon>mdi-thumb-up</v-icon>
                </v-btn>
                <v-btn color="primary" icon>
                    <v-icon>mdi-thumb-down</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card>
        <br/>
        <v-card :loading="loading">
            <v-card-title>
                <h2>Comments</h2>
            </v-card-title>
            <v-card-text>
                <v-row v-for="(comment, index) in comments" :key="index">
                    <v-col cols="12">
                        <h3>{{ comment.author }}</h3>
                        <p>{{ comment.message }}</p>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
        <br/>
        <v-card>
            <v-card-title>
                <h2>发表评论</h2>
            </v-card-title>
            <v-card-text>
                <v-textarea v-model="newComment.message" label="Message"></v-textarea>
                <v-btn @click="addComment" color="primary">发布</v-btn>
            </v-card-text>
        </v-card>
    </v-col>
</template>

<script setup>
import { ref } from 'vue';

const postTitle = ref('Post Title');
const postContent = ref('Post Content');
const loading = ref(false);
const comments = ref([
    {
        author: 'John Doe',
        message: 'This is a comment.',
    },
    {
        author: 'Jane Smith',
        message: 'Another comment.',
    },
]);
const newComment = ref({
    author: '',
    message: '',
});

function addComment() {
    comments.value.push({
        author: newComment.value.author,
        message: newComment.value.message,
    });
    newComment.value.author = '';
    newComment.value.message = '';
}

</script>

<style scoped>
</style>
