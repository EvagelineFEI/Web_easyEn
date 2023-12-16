package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.comments;
import com.easyen.easyenglish.mapper.commentMapper;
import com.easyen.easyenglish.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentServiceImpl implements commentService {
    @Autowired
    commentMapper commentMapper;

    @Override

    public List<comments> findAllComments(Integer post_id){
        return commentMapper.selectAllComments(post_id);
    }
    @Override
    public List<comments> findCommentsByPost(Integer postID, Integer offset, Integer pageSize){
        return commentMapper.selectCommentsByPost(postID, offset, pageSize);
    }
    @Override
    public void addComment(comments comment){
        if(comment == null){
            throw new IllegalArgumentException("参数不能为空！");
        }
        commentMapper.insertComment(comment);
    }
    @Override
    public void deleteComment(Integer commentID){
        try{
            commentMapper.deleteComment(commentID);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除帖子失败：" + e.getMessage());
        }
    }


}

