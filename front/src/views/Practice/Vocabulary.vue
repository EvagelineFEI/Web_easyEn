<script lang="ts" setup>
import practice, {WordReqeustParams} from "@/api/practice";
import configs from "@/configs";

const count = ref('3')  //正在练习第几个单词
const error = ref(false) //用户输入是否正确
const snackProvider = ref(false) //是否显示snackbar
const snackColor = ref('success') //snackbar的颜色
const snackProviderMessages = ref('') //snackbar的内容

const showAnswer = ref(false) //是否显示答案
const authStore = configs.stores.useAuthStore()
const errorMessages = ref('') //错误信息

const userInput = ref('') //用户输入的单词

const wordObject = ref({
  word_id: '1',
  en_word: 'abandon',
  phonetic_sign: 'əˈbændən',
  ch_word: '放弃，抛弃',
})

// window.addEventListener('keydown', (event) => {
//   if (event.key === 'Enter') {
//     checkInput();
//   }
// })

const snackHandle = (message: string, color: string) => {
  snackProviderMessages.value = message;
  snackColor.value = color;
  snackProvider.value = true;
}

const getNewWord = async () => {
  const params: WordReqeustParams = {
    is_six: 0,
    is_master: 0,
    is_today: 1
  }

  await practice.getNewWord(authStore.user as string, params)
      .then((response) => {
        const data = JSON.parse(response.resultData);
        if (response.code === 200) {
          userInput.value = '';
          wordObject.value.word_id = data.word_id;
          wordObject.value.en_word = data.en_word;
          wordObject.value.ch_word = data.ch_word;
          wordObject.value.phonetic_sign = data.phonetic_sign;
          snackHandle('获取新单词成功', 'success');
        } else {
          snackHandle(response.msg as string, 'error');
        }
      }).catch((error) => {
        snackHandle(error.toString(), 'error');
      })
}

const checkInput = async () => {
  error.value = false;
  const enteredWord = userInput.value;
  // 检查用户输入是否与单词相匹配
  if (enteredWord.toLowerCase() === wordObject.value.en_word.toLowerCase()) {
    const data = {
      word_id: wordObject.value.word_id as string,
    }
    await practice.updateUserWord(authStore.user as string, data)
        .then((response) => {
          if (response.code === 200) {
            snackHandle('已更新至用户记录', 'success');
            getNewWord();
          } else {
            snackHandle(response.msg as string, 'error');
          }
        }).catch((error) => {
          snackHandle(error.toString(), 'error');
        })
  } else {
    error.value = true;
    errorMessages.value = '拼写错误，请重新输入';
  }
}

const errorReset = () => {
  error.value = false;
  errorMessages.value = '';
}

getNewWord();
</script>

<template>
  <v-row>
    <v-col>
      <div class="title">今日单词：{{ count }}</div>
      <v-row class="box">
        <!-- 单词的输入框 -->
        <v-text-field
            v-model="userInput"
            :error="error"
            :error-messages="errorMessages"
            label="Please spell the word here"
            @change="errorReset"
        ></v-text-field>
      </v-row>
      <v-card class="box">
        <v-card-text>
          <v-row>
            <v-col class="text" cols="3">音标：</v-col>
            <v-col>{{ wordObject.phonetic_sign }}</v-col>
          </v-row>
        </v-card-text>
        <v-card-text class="text">
          <v-row>
            <v-col class="text" cols="3">单词释义：</v-col>
            <v-col>{{ wordObject.ch_word }}</v-col>
          </v-row>
        </v-card-text>
        <v-card-text class="text">
          <v-row>
            <v-col class="text" cols="3">答案：</v-col>
            <v-col v-if="showAnswer"> {{ wordObject.en_word }}</v-col>
            <v-col align-self="end" cols="auto">
            </v-col>
          </v-row>
        </v-card-text>
      </v-card>
      <v-btn
          class="button"
          color="info"
          variant="text"
          @click="showAnswer = !showAnswer">
        {{ showAnswer ? '隐藏答案' : '显示答案' }}
      </v-btn>
      <br/>
      <v-btn
          class="button"
          prepend-icon="mdi-upload"
          color="primary"
          @click="checkInput">
        提交
      </v-btn>
    </v-col>
  </v-row>

  <v-snackbar
      v-model="snackProvider"
      :color="snackColor"
      :timeout="2000">
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

<style scoped>
.title {
  margin: 10px;
  font-size: 12px;
  color: gray;
}

.text {
  font-size: 13px;
  color: gray;
}

.box {
  width: 70%;
  margin: 5% 15%;
}

.button {
  display: flex;
  left: 80%;
}
</style>