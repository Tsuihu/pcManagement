package com.higher.pcmanagement.controller;

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

    @PostMapping("/getPage.do")//请求地址
    ResultModel<PageResultBo<Point>> getPagePointCount(@RequestBody PageRequestBo model){
        PageResultBo<Point> pagePoint = pointService.getPagePoint(model);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,pagePoint,"分页查询成功");
    }
}
