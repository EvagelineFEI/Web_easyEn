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
interface SpeakTopicData {
    requirements: string,
    topic: string
}


interface WriteData {
    essay_id?: number,
    user_id?: number,
    essay_title?: string,
    essay_content?: string,
    essay_requirements?: string,
    correction_suggestions?: string,
    upload_date?: string,
    status?: string
}


const practice = {
    getArticle: async () => {
        return await requester<ArticleData>({
            url: "/article",
            method: "post"
        })
    },

    getNewWord: async (token: string, params: WordReqeustParams) => {
        return await requester<WordData>({
            url: "/word",
            method: "post",
            headers: {
                Authorization: token
            },
            params
        })
    },
    
    updateUserWord: async (token: string, params: WordData) =>  {
        return await requester<WordData>({
            url: "/user_practice",
            method: "post",
            headers: {
                Authorization: token
            },
            params
        })
    },

    submitEssay: async (token: string, data: WriteData) => {
        return await requester<WriteData>({
            url: "/essay/upload",
            method: "post",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    essayAns: async (token: string, data: WriteData) => {
        return await requester<WriteData>({
            url: "/suggestion/Suggestion",
            method: "post",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    deleteEssay: async (token: string, data: WriteData) => {
        return await requester<WriteData>({
            url: "/essay/delete",
            method: "delete",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    searchWrite: async (token: string) => {
        return await requester<WriteData[]>({
            url: "/essay/findByUser",
            method: "get",
            headers: {
                Authorization: token
            },
        })
    },

    getTopicAns: async (token: string,data: SpeakTopicData) => {
        return await requester<string>({
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
    SpeakTopicData,
    ArticleData,
    WordReqeustParams,
    WordData,
    WriteData,
}