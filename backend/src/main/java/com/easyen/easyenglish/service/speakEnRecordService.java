package com.easyen.easyenglish.service;

import com.easyen.easyenglish.entity.speakEnPracticeRecord;

import java.util.List;

public interface speakEnRecordService {
    void addRecord(speakEnPracticeRecord record);
    void deleteRecord(Integer record);
    void updateRecord(speakEnPracticeRecord record);
    List<speakEnPracticeRecord> getAllrecord();
    speakEnPracticeRecord findByTopic(String topic);
    String getSpeakResponce(String questions);
}
