package com.easyen.easyenglish.mapper;

import com.easyen.easyenglish.entity.comments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface commentMapper {
    // 查询所有评论

    List<comments> selectAllComments(Integer post_id);

    // 按照帖子实体查询评论
    List<comments> selectCommentsByPost(Integer postID, Integer offset, Integer pageSize);
    // 发帖
    void insertComment(comments comment);
    // 删除
    void deleteComment(Integer commentID);

}
