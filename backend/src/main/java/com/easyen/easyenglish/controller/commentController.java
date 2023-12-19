package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.comments;

import com.easyen.easyenglish.entity.post_name;
import com.easyen.easyenglish.service.commentService;
import com.easyen.easyenglish.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class commentController {
    @Autowired
    commentService commentService;

    // 显示所有评论
    @GetMapping("/getallcomments")

    public Result findAllComments(@RequestBody post_name post_){
        try{
            List<comments> comments = commentService.findAllComments(post_.getPost_id());
            return Result.success(comments);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }


    // 显示某一个帖子实体的最近10条评论
    @GetMapping("/getcomments/{postID}")
    public Result findCommentsByPost(@PathVariable("postID") Integer postID){
        try {
            // 计算偏移量
//            int offset = (page - 1) * pageSize;
            List<comments> comments = commentService.findCommentsByPost(postID);

            return Result.success(comments);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }
    // 发布评论
    //前端需要回传帖子ID，评论内容
    @PostMapping("/addcomments")
    public Result addComment(@RequestHeader("Authorization") String userJWT, @RequestBody comments comment){
        Integer userId = JwtUtil.getUserIdByJWT(userJWT);
        comment.setUser_id(userId);
        try{
            commentService.addComment(comment);
            return Result.success(comment);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    // 删除
    @DeleteMapping("/deleteComment")
    public Result deleteComment(@RequestHeader("Authorization") String userJWT, @RequestBody comments comment){
        Integer userId = JwtUtil.getUserIdByJWT(userJWT);
        if (userId != comment.getUser_id()){
            return Result.failure("非法请求, 你不是该评论的所有者");
        }
        try{
            commentService.deleteComment(comment.getComment_id());
            return Result.successCode();
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }
}

