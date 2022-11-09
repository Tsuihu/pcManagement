package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Sample;
import com.higher.pcmanagement.pojo.bo.SampleResultBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface SampleDao {
    List<SampleResultBo> getResultByPeopleId(@Param("peopleId")Integer peopleId);
    int getTubeIdByTubeCode(@Param("testtubeCode")String testtubeCode);
    int getCountByTubeCode(@Param("testtubeCode")String testtubeCode);
    void addSample(Sample sample);
}
