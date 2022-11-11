package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.CollectorDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.CollectorService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectorServiceImpl implements CollectorService {
    @Autowired
    CollectorDao collectorDao;

    /**
     * 分页查询
     * @param pageRequestBo
     * @return
     */
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
    public List<People> getAllName() {
        List<People> allName = collectorDao.getAllName();
        return allName;
    }

    /**
     * 删除采集人信息
     * @param collectorId
     */
    @Override
    public void deleteCollector(Integer collectorId) {
        collectorDao.deleteCollector(collectorId);
    }

    /**
     * 添加采集人信息
     * @param collector
     */
    @Override
    public void addCollector(Collector collector) throws BusinessException {
        if (collectorDao.getCountByIdcard(collector.getIdcard())>=1){
            throw new BusinessException("该身份证信息已存在", ResultCodeEnum.ERROR);
        }
        collectorDao.addCollector(collector);
    }

    /**
     * 修改采集人信息
     * @param collector
     */
    @Override
    public void updateCollector(Collector collector) throws BusinessException {

        System.out.println(collector.getIdcard());
        if(collectorDao.checkCollectorIdCard(collector.getIdcard()) > 0) {
            Collector collector1 = collectorDao.getCollectorIdCard(collector.getIdcard());
            String idcard1 = collector1.getIdcard();
            System.out.println(idcard1);
            if (!idcard1.equals(collector.getIdcard())){
                throw new BusinessException("身份证已存在",ResultCodeEnum.ERROR);
            }
        }
            collectorDao.updateCollector(collector);




    }


    @Override
    public List<Collector> getLikeNameIdCard(String name) {
        List<Collector> likeNameIdCard = collectorDao.getLikeNameIdCard(name);
        return likeNameIdCard;
    }
}
