package com.easyen.easyenglish.serviceimpl;

import com.easyen.easyenglish.entity.post_name;
import com.easyen.easyenglish.mapper.postMapper;
import com.easyen.easyenglish.service.postService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class postServiceImpl implements postService{
    @Autowired
    postMapper postMapper;

    @Override
    public List<post_name> findAllPost(Integer offset, Integer pageSize){
        return postMapper.findAllPost(offset, pageSize);
    }

    @Override
    public post_name findPostByID(Integer postID){
        return postMapper.selectPostById(postID);
    }

    @Override
    public List<post_name> findPostByUser(String user_name,Integer offset, Integer pageSize){
        return postMapper.selectPostByUser(user_name, offset, pageSize);
    }

    @Override
    public List<post_name> findPostByTitle_content(String postTitle, Integer offset, Integer pageSize){
        return postMapper.selectPostByTitle_content(postTitle, offset, pageSize);

    }


    @Override
    public void addPost(post_name post){
        if(post == null){
            throw new IllegalArgumentException("参数不能为空！");
        }
        // 插入到数据库
        postMapper.insertPost(post);
    }

    @Override
    public void updatePost(post_name post) {
        if(post.getTitle() == null || post.getContent() == null){
            throw new IllegalArgumentException("参数不能为空！");
        }
        postMapper.updatePost(post);
    }

    @Override
    public void deletePost(Integer postID) {
        try{
            postMapper.deletePost(postID);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("删除帖子失败：" + e.getMessage());
        }
    }


}
