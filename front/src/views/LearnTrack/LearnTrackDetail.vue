<script lang="ts" setup>
import {useAuthStore} from "@/configs/stores/authStore";
import learnTrack from "@/api/learnTrack"
import {useRoute} from "vue-router";

const wordNum = ref(3);
const CET4total = ref(1456);
const CET4prcatice = ref(2);
const CET6total = ref(1456);
const CET6prcatice = ref(1);
const CET4Progress = computed(() => CET4prcatice.value / CET4total.value * 100);
const CET6Progress = computed(() => CET6prcatice.value / CET6total.value * 100);

const oralList = ref([]);
const oralPraNum = computed(() => {
  return oralList.value.length ? oralList.value.length : 0;
});

const writeList = ref([]);
const writeNum = computed(() => {
  return writeList.value.length ?  writeList.value.length : 0;
});

const passageNum = ref('4');
const auth = useAuthStore();
const route = useRoute();


async function getDetails() {
  await learnTrack.getWordNum_Today(auth.user as string)
      .then((response) => {
        if (response.code === 200) {
          const data = JSON.parse(response.resultData);
          console.log(data);
          wordNum.value = data.word_number;
        }
      })

  await learnTrack.getWordNum_CET4()
  .then((response) => {
    if (response.code === 200) {
      const data = JSON.parse(response.resultData);
      CET4total.value = data.word_number;
    }
  })

  await learnTrack.getWordNum_CET6()
  .then((response) => {
    if (response.code === 200) {
      const data = JSON.parse(response.resultData);
      CET6total.value = data.word_number;
    }
  })

  await learnTrack.getSpeakProgress(auth.user as string)
  .then((response) => {
      if (response.code === 200) {
        oralList.value = response.resultData;
      }
  })

  await learnTrack.getEssayProgress(auth.user as string)
  .then((response) => {
    if (response.code === 200) {
      writeList.value = response.resultData;
    }
  })

  console.log(writeList.value)
}

const passages = ref([
  {
    id: '1',
    title: ' Tourism New Zealand website',
    content: 'New Zealand is a small country of four million inhabitants, a long-haul flight from all the major tourist-generating markets of the world.'
  },
  {
    id: '2',
    title: ' Tourism New Zealand website',
    content: 'New Zealand is a small country of four million inhabitants, a long-haul flight from all the major tourist-generating markets of the world.'
  },
]);

getDetails();
</script>

<template>
  <v-col>
    <v-row>
      <v-col cols="6">
        <v-card
            hover
            prepend-icon="mdi-spellcheck"
            title="单词练习进度">
          <v-divider class="border-opacity-75" thickness="2" color="primary"/>
          <v-card-text>
            今日练习单词数量：{{ wordNum }}
          </v-card-text>
          <v-card-text>
            四级单词练习进度：{{ CET4prcatice }}/{{ CET4total }}
            <v-progress-linear
                v-model="CET4Progress"
                :height="12"
                bg-color="blue-grey"
                color="#D1B6E1"
                rounded>
            </v-progress-linear>
          </v-card-text>
          <v-card-text>
            六级单词练习进度：{{ CET6prcatice }}/{{ CET6total }}
            <v-progress-linear
                v-model="CET6Progress"
                :height="12"
                bg-color="blue-grey"
                color="#FDD692"
                rounded>
            </v-progress-linear>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn variant="text" class="text-end" color="primary" to="/practice">
              继续学习
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>

      <v-col cols="6">
        <v-card
            hover
            prepend-icon="mdi-book-open-variant"
            title="阅读练习进度">
          <v-divider class="border-opacity-75" thickness="2" color="primary"/>
          <v-card-text>
            今日已阅读文章数量：{{ passageNum }}
          </v-card-text>
          <v-card-text>
            今日阅读外刊列表：
            <v-list lines="one" style="height: 80px; overflow-y: auto;">
              <v-data-iterator :items="passages">
                <template v-slot:default="{ items }">
                  <v-list-item
                      v-for="(item, index) in items"
                      :key="index + 1"
                      :class="index % 2 === 0 ? 'bg-grey-lighten-2' : ''"
                      :subtitle="item.raw.content"
                      :title="item.raw.title"
                  ></v-list-item>
                </template>
              </v-data-iterator>
            </v-list>

          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn variant="text" class="text-end" color="primary" to="/practice">
              继续学习
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

    <v-row class="row-style">
      <v-col cols="6">
        <v-card
            hover
            prepend-icon="mdi-account-voice"
            title="口语话题练习进度">
          <v-divider class="border-opacity-75" thickness="2" color="primary"/>
          <v-card-text>
            今日已进行口语话题练习次数：{{ oralPraNum }}
          </v-card-text>
          <v-card-text>
            今日口语话题列表：
            <v-list lines="one" style="height: 80px; overflow-y: auto;">
              <v-data-iterator :items="oralList">
                <template v-slot:default="{ items }">
                  <v-list-item
                      v-for="(item, index) in items"
                      :key="index + 1"
                      :class="index % 2 === 0 ? 'bg-grey-lighten-2' : ''"
                      :title="item.raw.practiced_topic"
                  ></v-list-item>
                </template>
              </v-data-iterator>
            </v-list>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn variant="text" class="text-end" color="primary" to="/practice">
              继续学习
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>


      <v-col cols="6">
        <v-card
            hover
            prepend-icon="mdi-pencil"
            title="写作练习进度">
          <v-divider class="border-opacity-75" thickness="2" color="primary"/>
          <v-card-text>
            今日已进行写作练习次数：{{ writeNum }}
          </v-card-text>
          <v-card-text>
            今日作文练习列表：
            <v-list lines="one" style="height: 80px; overflow-y: auto;">
              <v-data-iterator :items="writeList">
                <template v-slot:default="{ items }">
                  <v-list-item
                      v-for="(item, index) in items"
                      :key="index + 1"
                      :class="index % 2 === 0 ? 'bg-grey-lighten-2' : ''"
                      :title="item.raw.essay_title"
                  ></v-list-item>
                </template>
              </v-data-iterator>
            </v-list>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn variant="text" class="text-end" color="primary" to="/learn-track/write-details">
              查看详情
            </v-btn>
            <v-btn variant="text" class="text-end" color="primary" to="/practice/writing">
              继续学习
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-col>
</template>

<style scoped>
.row-style {
  margin-top: 20px;
}

.continue-learn {
  margin: 10px 10px 0 0;
  font-size: 14px;
  color: gray;
}
</style>