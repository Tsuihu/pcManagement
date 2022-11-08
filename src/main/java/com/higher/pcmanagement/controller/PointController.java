package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.PointService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    PointService pointService;


    /**
     * 分页查询
     * @param model pageIndex：第几页，pageSize：每页多少条
     * @return
     */
    @PostMapping("/getPage.do")//请求地址
    ResultModel<PageResultBo<Point>> getPagePointCount(@RequestBody PageRequestBo model){
        PageResultBo<Point> pagePoint = pointService.getPagePoint(model);

        return new ResultModel<>(ResultCodeEnum.SUCCESS,pagePoint,"分页查询成功");
    }

    /**
     * 添加监测点
     * @param point
     * @return
     * @throws BusinessException
     */
    @PostMapping("addPoint.do")
    ResultModel<Point> addPoint(@RequestBody  Point point) throws BusinessException {
        pointService.addPoint(point.getPointName());
        return new ResultModel<>(ResultCodeEnum.SUCCESS,point,"新检测点已添加");
    }

    /**
     * 根据pointId修改监测点
     * @param point
     * @return
     */
    @PostMapping("updatePoint.do")
    ResultModel<Point> updatePoint(@RequestBody Point point){
        pointService.updatePoint(point.getPointId(), point.getPointName());
        return new ResultModel<>(ResultCodeEnum.SUCCESS,point,"修改信息成功");
    }

    /**
     * 分局pointId删除检测点
     * @param pointId
     * @return
     */
    @PostMapping("deletePoint.do")
    ResultModel<String> deletePoint(Integer pointId){
        pointService.deletePoint(pointId);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,"信息删除成功");
    }
}
