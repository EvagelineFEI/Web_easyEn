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
    word_number: number,
}

const learnTrack = {
    getWordNum_CET4: () => {
        return requester<WordNumData>({
            url: "/word_all_four",
            method: "post",
        })
    },
    
    getWordNum_CET6: () => {
        return requester<WordNumData>({
            url: "/word_all_six",
            method: "post",
        })
    },

    getSpeakNum: (token: string) => {
        return requester<Number>({
            url: "/view-progress/speak-num",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },
    
    getEssayNum: (token: string) => {
        return requester<Number>({
            url: "/view-progress/essay-num",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },
    
    getSpeakProgress: (token: string) => {
        return requester<PronunciationData[]>({
            url: "/view-progress/speak-progress",
            method: "post",
            headers: {
                Authorization: token
            }
        })
    },
    
    getEssayProgress: (token: string) => {
        return requester<WritesData[]>({
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
    WritesData,
    PronunciationData,
}