<script setup lang="ts">

const count = ref('3')  //正在练习第几个单词
const wordObject = ref({
    word: 'abandon',
    userInput: '',
    phonetic: 'əˈbændən',
    meaning: '放弃，抛弃',
    isMastered: false
})

window.addEventListener('keydown', (event) => {
    if( event.key === 'Enter') {
        checkInput();
    }
})

const checkInput = () => {
    const enteredWord = wordObject.value.userInput;
    console.log('user entered:' , enteredWord);
    // 检查用户输入是否与单词相匹配
    if (enteredWord.toLowerCase() === wordObject.value.word.toLowerCase()) {
        wordObject.value.isMastered = true;
    } else {
        wordObject.value.isMastered = false;
    }
    console.log('用户输入是否正确：',wordObject.value.isMastered);
}

</script>

<template>
    <v-row>
        <v-col>
            <div class="title">今日单词：{{ count }}</div>
            <v-row class="box">
                <!-- 单词的输入框 -->
                <v-text-field label="Please spell the word here" v-model="wordObject.userInput"></v-text-field>
            </v-row>
            <v-card class="box">
                <v-card-text>
                    <v-row>
                        <v-col cols="3" class="text">音标：</v-col>
                        <v-col>{{ wordObject.phonetic }}</v-col>
                    </v-row>
                </v-card-text>
                <v-card-text class="text">
                    <v-row>
                        <v-col cols="3" class="text">单词释义： </v-col>
                        <v-col>{{wordObject.meaning}}</v-col>
                    </v-row>
                </v-card-text>
                <v-card-text class="text">
                    <v-row>
                        <v-col cols="3" class="text">查看答案：</v-col>
                        <v-col>{{wordObject.meaning}}</v-col>
                    </v-row>
                </v-card-text>
            </v-card>
            <v-btn @click="checkInput" color="primary" class="button">提交</v-btn>
        </v-col>
    </v-row>
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