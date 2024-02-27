package com.easyen.easyenglish.mapper;

import com.easyen.easyenglish.entity.correctionFeedback;
import com.easyen.easyenglish.entity.essay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface correctionFeedbackMapper {
    // 增加批改建议
    void addFeedback(correctionFeedback correctionFeedback);
    // 删除批改建议
    void deleteFeedback(Integer feedbackId);
    // 更新批改建议
    void updateFeedback(correctionFeedback correctionFeedback);
    // 根据批改编号查询建议
    correctionFeedback findByID(Integer feedbackId);
    // 根据作文号查询建议
    List<correctionFeedback> findByEssay(Integer essayID);
}
