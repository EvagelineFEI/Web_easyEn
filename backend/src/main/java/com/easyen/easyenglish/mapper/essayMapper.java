package com.easyen.easyenglish.mapper;

import com.easyen.easyenglish.entity.essay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface essayMapper {
    // 查询数据库所有作文
    List<essay> getAllEssays();
    // 根据作文号查询作文
    essay findByID(Integer essayId);
    // 根据用户号查询作文
    List<essay> findByUser(Integer userID);
    // 根据作文标题模糊查询作文
    List<essay> findEssaysByTitle(String essayTitle);
    // 增加作文
    void addEssay(essay essay);
    // 删除作文
    void deleteEssay(Integer essayId);
    // 更新作文
    void updateEssay(essay essay);
}
