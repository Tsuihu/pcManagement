package com.higher.pcmanagement.service;

import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface CollectorService {

    PageResultBo<Collector> getPageCollector(PageRequestBo pageRequestBo);

    void deleteCollector(Integer collectorId);

    void addCollector(Collector collector);

    void updateCollector(Collector collector);
}
