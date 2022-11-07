package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.CollectorDao;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectorServiceImpl implements CollectorService {
    @Autowired
    CollectorDao collectorDao;

    @Override
    public PageResultBo<Collector> getPageCollector(PageRequestBo pageRequestBo) {
        if(pageRequestBo.getPageIndex()<0){
            pageRequestBo.setPageIndex(0);
        }
        List<Collector> list = collectorDao.getPageCollector(pageRequestBo);
        Integer count = collectorDao.getPageCollectorCount();
        PageResultBo<Collector> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }

    @Override
    public void deleteCollector(Integer collectorId) {
        collectorDao.deleteCollector(collectorId);
    }

    @Override
    public void addCollector(Collector collector) {
        collectorDao.addCollector(collector);
    }

    @Override
    public void updateCollector(Collector collector) {
        collectorDao.updateCollector(collector);
    }
}
