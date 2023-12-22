import {requester} from "@/api/utils";

// 帖子相关数据结构
interface UserPostData {
    post_id?: number,
    state?:string,
    user_id?: number,
    title:string,
    content:string,
    public_or:boolean,
    time?:string
}

interface CommentData {
    comment_id?: number,
    post_id: number,
    user_id?: number,
    contents: string,
    comment_time?: string,
    comment_state?: string,
}

interface PostComment {
    posts: UserPostData,
    comments: CommentData[]
}

interface PostPages {
    page: number,
    pageSize: number
}

const communicate = {
    addPost: (token: string, data: UserPostData) => {
        return requester<UserPostData>({
            url: "/post/addPost",
            method: "post",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    getAllPost: () => {
        return requester<UserPostData[]>({
            url: "/post/returnAll",
            method: "get",
        })
    },

    showPostComment: (postId: number) => {
        return requester<PostComment>({
            url: "/post/returnbyid/" + postId,
            method: "get",
        })
    },

    showPostByUser: (userId: number, params: PostPages) => {
        return requester<CommentData[]>({
            url: "/post/returnbyuser/" + userId,
            method: "post",
            params,
        })
    },

    searchPostByTitle: (titleContent: string, params: PostPages) => {
        return requester({
            url: "/post/returnbytitle/" + titleContent,
            method: "post",
            params,
        })
    },

    deletePost: (token: string, postId: number, data: UserPostData) => {
        return requester({
            url: "/post/deletePost/" + postId,
            method: "delete",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    changePost: (token: string, data: UserPostData) => {
        return requester<UserPostData>({
            url: "/post/updatePost",
            method: "post",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    postComment: (token: string, data: CommentData) => {
        return requester<CommentData>({
            url: "/comment/addcomments",
            method: "post",
            headers: {
                Authorization: token
            },
            data,
        })
    },

    deleteComment: (token: string, data: CommentData) => {
        return requester({
            url: "/comment/deletecomments",
            method: "delete",
            headers: {
                Authorization: token
            },
            data,
        })
    }
}

export default communicate;

export type {
    PostComment,
    UserPostData,
    PostPages,
    CommentData,
}