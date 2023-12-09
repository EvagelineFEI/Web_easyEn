package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.comments;
import com.easyen.easyenglish.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class commentController {
    @Autowired
    commentService commentService;

    // 显示所有评论
    @GetMapping("/getallcomments")
    public Result findAllComments(){
        try{
            List<comments> comments = commentService.findAllComments();
            return new Result(comments, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 显示某一个帖子实体的最近10条评论
    @GetMapping("/getcomments/{postID}")
    public Result findCommentsByPost(@PathVariable("postID") Integer postID,
                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                     @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize){
        try {
            // 计算偏移量
            int offset = (page - 1) * pageSize;
            List<comments> comments = commentService.findCommentsByPost(postID, offset, pageSize);
            return new Result(comments, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 发帖
    @PostMapping("/addcomments/{userID}")
    public Result addComment(@RequestBody comments comment, @PathVariable("userID") Integer userID){
        comment.setUser_id(userID);
        try{
            commentService.addComment(comment);
            return new Result(comment, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 删除
    @DeleteMapping("/deleteComment/{commentID}")
    public Result deleteComment(@PathVariable("commentID") Integer commentID){
        try{
            commentService.deleteComment(commentID);
            return new Result(commentID, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }
}
