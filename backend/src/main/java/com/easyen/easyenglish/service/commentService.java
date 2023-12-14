package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.comments;

import java.util.List;

public interface commentService {
    List<comments> findAllComments();
    List<comments> findCommentsByPost(Integer postID, Integer offset, Integer pageSize);
    void addComment(comments comment);
    void deleteComment(Integer commentID);

}