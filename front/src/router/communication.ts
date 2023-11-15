import WritePost from "@/views/Communication/WritePost.vue";
import Communication from "@/views/Communication.vue";
import Post from "@/views/Communication/Post.vue";

export default [
    {
        path: '',
        name: 'Communication',
        component: Communication,
    },
    {
        path: 'write',
        name: 'Write',
        component: WritePost,
    },
    {
        path: 'post/:id',
        name: 'Post',
        component: Post,
    }
]