import {requester} from "@/api/utils";

interface WritesData {
    essay_id: number,
    user_id?: number,
    essay_title: string,
    esaay_content: string,
    essay_requirements: string,
    upload_date: string,
    status?: number,
}

interface PronunciationData {
    id: number,
    user_id: number,
    practice_topic: string,
    topic_answer: string,
    practice_date: string,
}

interface WordNumData {
    errno: number,
    word_number?: number,
    num?: number,
}

const learnTrack = {
    getWordNum_CET4: async () => {
        return await requester<WordNumData>({
            url: "/word_all_four",
            method: "post",
        })
    },

    getWordNum_CET6: async () => {
        return await requester<WordNumData>({
            url: "/word_all_six",
            method: "post",
        })
    },

    getUserWord_CET4: async (token: string) => {
        return await requester<WordNumData>({
            url: "/user_master_four",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },

    getUserWord_CET6: async (token: string) => {
        return await requester<WordNumData>({
            url: "/user_master_six",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },

    getWordNum_Today: async (token: string) => {
        return await requester<WordNumData>({
            url: "/word_practice",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },
    getSpeakNum: async (token: string) => {
        return await requester<Number>({
            url: "/view-progress/speak-num",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },

    getEssayNum: async (token: string) => {
        return await requester<Number>({
            url: "/view-progress/essay-num",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },

    getSpeakProgress: async (token: string) => {
        return await requester<PronunciationData[]>({
            url: "/view-progress/speak-progress",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },

    getEssayProgress: async (token: string) => {
        return await requester<WritesData[]>({
            url: "/view-progress/essayprogress",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },
}

export default learnTrack;

export type {
    WordNumData,
    WritesData,
    PronunciationData,
}