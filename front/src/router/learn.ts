import Speaking from "@/views/Practice/Speaking.vue";
import Writing from "@/views/Practice/Writing.vue";
import Vocabulary from "@/views/Practice/Vocabulary.vue";
import Articles from "@/views/Practice/Article/Articles.vue";
import Article from "@/views/Practice/Article/Article.vue";
import Learning from "@/views/Practice.vue";

export default [
    {
        path: '',
        name: 'Practice',
        component: Learning,
    },
    {
        path: 'speaking',
        name: 'Speaking',
        component: Speaking,
    },
    {
        path: 'writing',
        name: 'Writing',
        component: Writing,
    },
    {
        path: 'vocabulary',
        name: 'Vocabulary',
        component: Vocabulary,
    },
    {
        path: 'articles',
        name: 'Articles',
        component: Articles,
    },
    {
        path: 'article/:id', // 依靠文章id来做内容获取
        name: 'Article',
        component: Article,
    },
]