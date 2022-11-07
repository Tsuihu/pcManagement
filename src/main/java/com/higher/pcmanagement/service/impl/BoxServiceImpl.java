package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.BoxDao;
import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public class BoxServiceImpl implements BoxService {
    @Autowired
    BoxDao boxDao;

    @Override
    public PageResultBo<Box> getPageBox(PageRequestBo pageRequestBo) {
        if(pageRequestBo.getPageIndex()<0){
            pageRequestBo.setPageIndex(0);
        }
        List<Box> list = boxDao.getPageBox(pageRequestBo);
        Integer count = boxDao.getPageBoxCount();
        PageResultBo<Box> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }

    @Override
    public void deleteBox(Integer boxId) {
        boxDao.deleteBox(boxId);
    }

    @Override
    public void addBox(Box box) {
        boxDao.addBox(box);
    }

    @Override
    public void updateBox(Box box) {
        boxDao.updateBox(box);
    }
}
