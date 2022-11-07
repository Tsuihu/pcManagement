package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PointDao {
    //分页查询，
    List<Point> getPagePoint(PageRequestBo model);

    //查询每页信息的行数
    Integer getPagePointCount();
}
