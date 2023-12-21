package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.comments;
import com.easyen.easyenglish.entity.post_name;
import com.easyen.easyenglish.service.commentService;
import com.easyen.easyenglish.service.postService;

import com.easyen.easyenglish.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class postController {
    @Autowired
    postService postService;
    @Autowired
    commentService commentService;

    // 添加帖子

    @PostMapping("/addPost")
    public Result addPost(@RequestHeader("Authorization") String userJWT,@RequestBody post_name post){
        Integer userId = JwtUtil.getUserIdByJWT(userJWT);
        post.setUser_id(userId);
        try {
            postService.addPost(post);
            System.out.println("Response body: " + post);
            return Result.success(post);
        }catch (Exception e){
            System.out.println("error: " + e.getMessage());
            return Result.failure(e.getMessage());
        }
    }

    // 查询所有帖子信息,分页
    // 页面初始状态

    @GetMapping("/returnAll")
    public Result findAllPost() {
        try{
            List<post_name> posts = postService.findAllPost();
            return Result.success(posts);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    // 按照potsid查询
    //点击某条帖子，会拉出所有的评论
    @GetMapping("/returnbyid/{postID}")
    public Result findPostByID(@PathVariable("postID") Integer postID){
        try{
            // 查询帖子内容
            post_name posts = postService.findPostByID(postID);
            // 查询对应的评论
//            int offset = (page - 1) * pageSize;
            List<comments> comments = commentService.findCommentsByPost(postID);
            // 构建结果对象

            Map<String, Object> result = new HashMap<>();
            result.put("posts", posts);
            result.put("comments", comments);
            System.out.println(Result.success(comments));
            return Result.success(comments);

        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    // 按照用户查找
    // 查找某用户发的所有帖子
    @GetMapping("/returnbyuser/{userName}")
    public Result findPostByUser(@PathVariable("userName") String userName,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        try{
            // 分页查询该用户发的所有帖子
            int offset = (page - 1) * pageSize;
            // System.out.print(userID);
            // System.out.print(offset);
            List<post_name> posts = postService.findPostByUser(userName, offset, pageSize);
            // System.out.print(posts);
            // 遍历每一个帖子，查找对应的评论
            Map<post_name, List<comments>> postCommentMap = new HashMap<>();
            for (post_name post : posts) {
                //System.out.print(post.getUser_id());
                List<comments> comments= commentService.findCommentsByPost(post.getPost_id());
                //System.out.print(comments);
                postCommentMap.put(post, comments);
            }
            return Result.success(postCommentMap);
        }catch (Exception e){
            return Result.failure("发生未知错误:" + e.getMessage());
        }
    }

    // 按照标题或者内容模糊查找(按关键词搜索)
    @GetMapping("/returnbytitle/{titleContent}")
    public Result findPostByTitle(@PathVariable("titleContent") String postTitle,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        try{
            // 分页查询帖子
            int offset = (page - 1) * pageSize;
            List<post_name> posts = postService.findPostByTitle_content(postTitle, offset, pageSize);
            // 遍历每一个帖子，查找对应的评论
            Map<post_name, List<comments>> postCommentMap = new HashMap<>();
            for (post_name post : posts) {
                List<comments> comments = commentService.findCommentsByPost(post.getPost_id());
                postCommentMap.put(post, comments);
            }
            return Result.success(postCommentMap);
        }catch (Exception e){
            return Result.failure("发生未知错误:" + e.getMessage());
        }
    }

    // 更新
    @PostMapping("/updatePost")
    public Result updatePost(@RequestHeader("Authorization") String userJWT,@RequestBody post_name post){
        Integer user_id = JwtUtil.getUserIdByJWT(userJWT);
        if (user_id != post.getUser_id()) {
            return Result.failure("无法修改, 你不是该笔记的所有者");
        }
        try {
            postService.updatePost(post);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
        return Result.success("帖子修改成功\n"+post);
    }

    // 删除
    @DeleteMapping("/deletePost")
    public Result deletePost(@RequestHeader("Authorization") String userJWT,@RequestBody post_name post){
        Integer userId = JwtUtil.getUserIdByJWT(userJWT);
        if (userId != post.getUser_id()){
            return Result.failure("非法请求, 你不是该帖子的所有者");
        }
        try {
            postService.deletePost(post.getPost_id());
            return Result.success("成功删除"+post.getPost_id());
        }catch (Exception e){
            return Result.failure("发生未知错误:" + e.getMessage());
        }
    }
}