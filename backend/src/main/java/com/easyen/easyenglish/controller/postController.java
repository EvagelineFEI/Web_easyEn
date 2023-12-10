package com.easyen.easyenglish.controller;

import com.easyen.easyenglish.dto.Result;
import com.easyen.easyenglish.entity.comments;
import com.easyen.easyenglish.entity.post_name;
import com.easyen.easyenglish.service.commentService;
import com.easyen.easyenglish.service.postService;
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
    @PostMapping("/addPost/{userID}")
    public Result addPost(@RequestBody post_name post, @PathVariable("userID") Integer userID){
        post.setUser_id(userID);
        try {
           postService.addPost(post);
           return new Result(post, 200);
        }catch (Exception e){
           return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 查询所有帖子信息,分页
    @GetMapping("/returnAll")
    public Result findAllPost( @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try{
            int offset = (page - 1) * pageSize;
            List<post_name> posts = postService.findAllPost(offset, pageSize);
            return new Result(posts, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 按照potsid查询
    @GetMapping("/returnbyid/{postID}")
    public Result findPostByID(@PathVariable("postID") Integer postID,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        try{
            // 查询帖子内容
            post_name posts = postService.findPostByID(postID);

            // 查询对应的评论
            int offset = (page - 1) * pageSize;
            List<comments> comments = commentService.findCommentsByPost(postID, offset, pageSize);

            // 构建结果对象
            Map<String, Object> result = new HashMap<>();
            result.put("posts", posts);
            result.put("comments", comments);

            return new Result(result, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 按照用户查找
    @GetMapping("/returnbyuser/{userID}")
    public Result findPostByUser(@PathVariable("userID") Integer userID,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        try{
            // 分页查询该用户发的所有帖子
            int offset = (page - 1) * pageSize;
            // System.out.print(userID);
            // System.out.print(offset);
            List<post_name> posts = postService.findPostByUser(userID, offset, pageSize);
            // System.out.print(posts);


            // 遍历每一个帖子，查找对应的评论
            Map<post_name, List<comments>> postCommentMap = new HashMap<>();
            for (post_name post : posts) {
                //System.out.print(post.getUser_id());
                List<comments> comments= commentService.findCommentsByPost(post.getPost_id(), offset, pageSize);
                //System.out.print(comments);
                postCommentMap.put(post, comments);
            }
            return new Result(postCommentMap, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 按照标题内容模糊查找
    @GetMapping("/returnbytitle/{postTitle}")
    public Result findPostByTitle(@PathVariable("postTitle") String postTitle,
                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        try{
            // 分页查询帖子
            int offset = (page - 1) * pageSize;
            List<post_name> posts = postService.findPostByTitle(postTitle, offset, pageSize);

            // 遍历每一个帖子，查找对应的评论
            Map<post_name, List<comments>> postCommentMap = new HashMap<>();
            for (post_name post : posts) {
                List<comments> comments = commentService.findCommentsByPost(post.getPost_id(), offset, pageSize);
                postCommentMap.put(post, comments);
            }

            return new Result(postCommentMap, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 更新
    @PostMapping("/updatePost")
    public Result updatePost(@RequestBody post_name post, @PathVariable("userID") Integer userID){
        post.setUser_id(userID);
        try {
            postService.updatePost(post);
            return new Result(post, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }

    // 删除
    @DeleteMapping("/deletePost/{postID}")
    public Result deletePost(@PathVariable("postID") Integer postID){
        try {
            postService.deletePost(postID);
            return new Result(postID, 200);
        }catch (Exception e){
            return new Result("发生未知错误:" + e.getMessage(), 500);
        }
    }
}
