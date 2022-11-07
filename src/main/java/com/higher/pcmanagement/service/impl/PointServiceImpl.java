package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.PointDao;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointDao pointDao;

    @Override
    public PageResultBo<Point> getPagePoint(PageRequestBo model) {
        if (model.getPageIndex()< 0){
            model.setPageIndex(0);
        }
        List<Point> list = pointDao.getPagePoint(model);
        Integer count = pointDao.getPagePointCount();
        PageResultBo<Point> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }
}
