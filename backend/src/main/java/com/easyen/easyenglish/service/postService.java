package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.post_name;

import java.util.Date;
import java.util.List;

public interface postService {
    void addPost(post_name post);

    List<post_name> findAllPost();

    post_name findPostByID(Integer postID);

    List<post_name> findPostByUser(Integer userName, Integer offset, Integer pageSize);

    List<post_name> findPostByTitle_content(String postTitle, Integer offset, Integer pageSize);

    void updatePost(post_name post);

    void deletePost(Integer postID);
    List<post_name> findPostByTime(String startDate, String endDate);
}

