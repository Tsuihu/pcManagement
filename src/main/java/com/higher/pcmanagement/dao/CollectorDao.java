package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface CollectorDao {
    List<Collector> getPageCollector(PageRequestBo pageRequestBo);
    Integer getPageCollectorCount();

    List<People> getAllName();

    void deleteCollector(@Param("collectorId") Integer collectorId);

    void addCollector(Collector collector);
    int getCountByIdcard(@Param("idcard")String idcard);

    void updateCollector(Collector collector);

    List<Collector> getLikeNameIdCard(@Param("name") String name);
}
