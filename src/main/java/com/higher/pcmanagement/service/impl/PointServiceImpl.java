package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.PointDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.PointService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class PointServiceImpl implements PointService {

    @Autowired
    PointDao pointDao;

    /**
     * 分页查询
     * @param model
     * @return
     */
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

    /**
     * 添加监测点
     * @param pointName
     * @throws BusinessException
     */
    @Override
    public void addPoint( String pointName) throws BusinessException {
        Point point = pointDao.getPointByName(pointName);
//        判断检测点是否已被添加过
        if (StringUtils.isEmpty(point)){
            pointDao.addPoint(pointName);
        }else {
            throw new BusinessException("该检测点已添加过", ResultCodeEnum.ERROR);
        }

    }

    /**
     * 根据pointId,更新监测点
     * @param pointId
     * @param pointName
     */
    @Override
    public void updatePoint(Integer pointId,String pointName) {
        pointDao.updatePoint(pointId,pointName);
    }

    /**
     * 根据pointId 删除检测点
     * @param pointId
     */
    @Override
    public void deletePoint(Integer pointId) {
        pointDao.deletePoint(pointId);
    }

    @Override
    public List<Point> getAllPointName() {
        return pointDao.getAllPointName();
    }
}
