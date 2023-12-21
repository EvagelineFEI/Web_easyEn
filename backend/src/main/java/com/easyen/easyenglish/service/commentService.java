package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.comments;

import java.util.List;

public interface commentService {

    List<comments> findAllComments(Integer post_id);
    List<comments> findCommentsByPost(Integer postID);
    void addComment(comments comment);
    void deleteComment(Integer commentID);

}
