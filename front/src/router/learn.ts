import Speaking from "@/views/Practice/Speaking.vue";
import Writing from "@/views/Practice/Writing.vue";
import Vocabulary from "@/views/Practice/Vocabulary.vue";
import Articles from "@/views/Practice/Articles.vue";
//import Learning from "@/views/Practice.vue";

export default [
  {
    path: "",
    name: "Practice",
    redirect: "/practice/articles",
  },
  {
    path: "speaking",
    name: "Speaking",
    component: Speaking,
  },
  {
    path: "writing",
    name: "Writing",
    meta: {
      needAuth: true
    },
    component: Writing,
  },
  {
    path: "vocabulary",
    name: "Vocabulary",
    meta: {
      needAuth: true
    },
    component: Vocabulary,
  },
  {
    path: "articles",
    name: "Articles",
    component: Articles,
  },
];
