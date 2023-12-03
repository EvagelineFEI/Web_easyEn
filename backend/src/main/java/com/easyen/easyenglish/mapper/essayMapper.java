package com.easyen.easyenglish.mapper;

import com.easyen.easyenglish.entity.essay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface essayMapper {
    List<essay> getAllEssays();
}
