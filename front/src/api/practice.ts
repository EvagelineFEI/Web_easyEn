import {requester} from "@/api/utils";

interface ArticleData {
    pic_url: string,
    article_url: string,
    article_name: string,
    article_descr: string,
}

interface WordReqeustParams {
    is_six: number,
    is_master: number,
    is_today: number,
}

interface WordData {
    errno?: number,
    word_id?: string,
    en_word?: string,
    ch_word?: string,
    phonetic_sign?: string,
}
export interface SpeakTopicData {
    requirements: string,
    topic: string
}


interface WriteData {
    essay_id?: number,
    user_id?: number,
    essay_title: string,
    essay_content: string,
    essay_requirements: string,
    upload_date?: string,
    status?: string
}


const practice = {
    getArticle: () => {
        return requester<ArticleData>({
            url: "/article",
            method: "post"
        })
    },

    getNewWord: (token: string, params: WordReqeustParams) => {
        return requester<WordData>({
            url: "/word",
            method: "post",
            headers: {
                Authorization: token
            },
            params
        })
    },
    
    updateUserWord: (token: string, params: WordData) =>  {
        return requester<WordData>({
            url: "/user_practice",
            method: "post",
            headers: {
                Authorization: token
            },
            params
        })
    },

    submitEssay: (token: string, data: WriteData) => {
        return requester<WriteData>({
            url: "/essay/upload",
            method: "post",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    searchWrite: (token: string) => {
        return requester<WriteData[]>({
            url: "/essay/findByUser",
            method: "get",
            headers: {
                Authorization: token
            },
        })
    },

    getTopicAns: (token: string,data: SpeakTopicData) => {
        return requester<string>({
            url: "/speaken/speakFeedback",
            method: "post",
            headers: {
                Authorization: token
            },
            data
        })
    }

}

export default practice;

export type {
    ArticleData,
    WordReqeustParams,
    WordData,
    WriteData,
}