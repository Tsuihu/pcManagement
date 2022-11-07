package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface PointService {
    //分页查询
    PageResultBo<Point> getPagePoint(PageRequestBo model);

    //添加检测点
    void addPoint(String pointName) throws BusinessException;

//    修改检测点
    void updatePoint(Integer pointId,String pointName);

//    删除监测点
    void deletePoint(Integer pointId);
}
