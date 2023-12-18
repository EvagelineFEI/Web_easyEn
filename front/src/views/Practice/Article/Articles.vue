<script setup lang="ts">
import practice, {ArticleData} from "@/api/practice";
import {Ref} from "vue";

const articles: Ref<ArticleData[]> = ref([
  {
    article_id: '1',
    article_name: 'abandon',
    article_descr: 'əˈbændən',
    pic_url: '放弃，抛弃',
    article_url: 'https://www.baidu.com'
  },
  {
    article_id: '2',
    article_name: 'abandon',
    article_descr: 'əˈbændən',
    pic_url: '放弃，抛弃',
    article_url: 'https://www.baidu.com'
  },
  {
    article_id: '3',
    article_name: 'abandon',
    article_descr: 'əˈbændən',
    pic_url: '放弃，抛弃',
    article_url: 'https://www.baidu.com'
  },
  {
    article_id: '4',
    article_name: 'abandon',
    article_descr: 'əˈbændən',
    pic_url: '放弃，抛弃',
    article_url: 'https://www.baidu.com'
  },
  {
    article_id: '5',
    article_name: 'abandon',
    article_descr: 'əˈbændən',
    pic_url: '放弃，抛弃',
    article_url: 'https://www.baidu.com'
  },
  {
    article_id: '6',
    article_name: 'abandon',
    article_descr: 'əˈbændən',
    pic_url: '放弃，抛弃',
    article_url: 'https://www.baidu.com'
  },
]);
const loading = ref(false);

async function refreshArticles() {
  loading.value = true;
  for (let i = 0; i < 6; i++) {
    await practice.getArticle()
      .then((response) => {
        const data = JSON.parse(response.resultData)
        if (response.code === 200) {
          articles.value[i] = data;
        }
      })
  }
  loading.value = false;
}

refreshArticles();
</script>

<template>
  <v-container>
    <v-row justify="start">
      <v-col cols="2">
        <v-btn @click="refreshArticles" icon="mdi-refresh" color="primary" />
      </v-col>
    </v-row>
    <v-row>
      <v-col v-for="article in articles" cols="12" sm="6" md="4">
        <v-card :href="article.article_url">
          <v-skeleton-loader
              :loading="loading"
              height="350"
              type="image, list-item-two-line"
          >
            <v-responsive>
              <v-img
                  class="align-end text-white"
                  cover
                  :src="article.pic_url"
              >
              </v-img>
              <v-card-text class="text-h6">
                {{ article.article_name }}
              </v-card-text>
              <v-card-text>
                {{ article.article_descr }}
              </v-card-text>
            </v-responsive>
          </v-skeleton-loader>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>