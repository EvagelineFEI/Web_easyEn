<script setup lang="ts">
const props = defineProps([
    'status'
])

const emit = defineEmits([
    'update:status'
])

const back = () => {
    emit('update:status', 'write')
}


const ans = {
    title: "study",
    content: [
        "In many countries, schools have severe problems with student behavior.",
        "What do you think are the causes of this? What solutions can you suggest?",
        "Poor student behavior seems to be an increasingly widespread problem and I think that modern lifestyles are probably responsible for this.",
        "In many countries, the birth rate is decreasing so that families are smaller with fewer children. These children are often spoilt, not in terms of love and attention because working parents do not have the time for this, but in more material ways. They are allowed to have whatever they want, regardless of price, and to behave as they please. This means that the children grow up without consideration for others and without any understanding of where their standard of living comes from.",
        "When they get to school age they have not learnt any self-control or discipline. They have less respect for their teachers and refuse to obey school rules in the way that their parents did. Teachers continually complain about this problem and measures should be taken to combat the situation. But I think the solution to the problem lies with the families, who need to be more aware of the future consequences of spoiling their children. If they could raise them to be considerate of others and to be social, responsible individuals, the whole community would benefit.",
        "Perhaps parenting classes are needed to help them to do this, and high-quality nursery schools could be established that would support families more in terms of raising the next generation. The government should fund this kind of parental support because this is no longer a problem for individual families but for society as a whole."
    ],
    advice: [
        "文章提及问题：学校面临学生行为问题的严重性。",
        "表达清晰度：文章整体表达清晰，但有几处表述可以更加简练。例如，“这意味着孩子们长大后”这句话可以简化为“这导致孩子们在长大后”以提高句子的流畅度。",
        "段落结构：段落之间的过渡较为顺畅，但在探讨解决方案时可以考虑明确划分成一个独立的段落，使结构更加清晰。",
        "论述连贯性：在讨论学生行为问题的原因时，可以更详细地展开，列举一两个具体例子来支持观点，以增强论述的连贯性和可信度。",
        "解决方案：对解决方案的探讨较为明确，提到了家庭的重要性。然而，在引入父母培训和高质量托儿所时，可以更详细地说明这些解决方案是如何实施的，以及它们将如何产生积极影响。",
        "语言风格：文章的语言风格较为正式，可以考虑在表达观点时加入一些生动的词汇或例子，使文章更引人入胜。"
    ],
}

</script>

<template>
<v-row justify="start">
    <v-col cols="2">
        <v-btn
            icon="mdi-arrow-left"
            @click="back"
        >
        </v-btn>
    </v-col>
</v-row>

<br/>

<v-card>
    <v-card-title>{{ans.title}}</v-card-title>
    <v-card-text v-for="content in ans.content">
        {{content}}
    </v-card-text>
</v-card>

<br/>

<v-textarea
    readonly
    v-model="ans.advice"
    label="作文建议"
    variant="outlined"
    density="comfortable"
    rows="10"
/>
</template>
