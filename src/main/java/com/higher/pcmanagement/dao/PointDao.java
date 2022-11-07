package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PointDao {
    //分页查询，
    List<Point> getPagePoint(PageRequestBo model);

    //查询每页信息的行数
    Integer getPagePointCount();

//    添加检测点
    void addPoint(@Param("pointName") String pointName);

//    根据名字查询该信息，用于判断检测点是否已存在
    Point getPointByName(@Param("pointName") String pointName);

//    修改检测点
    void updatePoint(@Param("pointId") Integer pointId, @Param("pointName") String pointName);

//    删除检测点
    void deletePoint(@Param("pointId") Integer pointId);
}
