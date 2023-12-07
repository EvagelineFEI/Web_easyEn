package com.easyen.easyenglish.mapper;

import com.easyen.easyenglish.entity.speakEnPracticeRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface speakEnRecordMapper {
    void addRecord(speakEnPracticeRecord record);
    void deleteRecord(Integer record_id);
    void updateRecord(speakEnPracticeRecord record);
    List<speakEnPracticeRecord> getAllRecord();
    speakEnPracticeRecord findByTopic(String topic);
}
