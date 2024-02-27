import Speaking from "@/views/Practice/Speaking";
import Writing from "@/views/Practice/Writing";
import Vocabulary from "@/views/Practice/Vocabulary";
import Articles from "@/views/Article/Articles";
import Article from "@/views/Article/Article";

export default [
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