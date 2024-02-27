package com.easyen.easyenglish.mapper;

import com.easyen.easyenglish.entity.post_name;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface postMapper {
    List<post_name> findAllPost();
    // 插入帖子
    void insertPost(post_name post);
    // 按照postid查找
    post_name selectPostById(Integer postID);
    // 按照用户查找
    List<post_name> selectPostByUser(String userName, Integer offset, Integer pageSize);
    // 标题模糊查询
    List<post_name> selectPostByTitle_content(String postTitleContent, Integer offset, Integer pageSize);
    // 更新
    void updatePost(post_name post);
    // 删除
    void deletePost(Integer postID);
    List<post_name> findPostByTime(String startDate,String endDate);

}
