package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface CollectorService {

    PageResultBo<Collector> getPageCollector(PageRequestBo pageRequestBo);

    List<People> getAllName();

    void deleteCollector(Integer collectorId);

    void addCollector(Collector collector) throws BusinessException;

    void updateCollector(Collector collector);
}
