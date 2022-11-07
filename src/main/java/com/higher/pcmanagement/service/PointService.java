package com.higher.pcmanagement.service;

import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import org.springframework.stereotype.Component;

@Component
public interface PointService {
    PageResultBo<Point> getPagePoint(PageRequestBo model);
}
