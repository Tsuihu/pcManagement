package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.CollectorService;
import com.higher.pcmanagement.util.MD5;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/collector")
public class CollectorController {
    @Autowired
    CollectorService collectorService;

    /**
     * 分页查询
     * @param pageRequestBo  pageIndex：第几页，pageSize：每页多少条
     * @return
     */
    @PostMapping("/getPageCollector.do")
    public ResultModel<PageResultBo<Collector>> getPageCollector(@RequestBody PageRequestBo pageRequestBo){
        PageResultBo<Collector> listCollector= collectorService.getPageCollector(pageRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, listCollector, "检测人员分页查询成功");
    }

    /**
     * 全查人名
     * @return
     */
    @PostMapping("/getAllName.do")
    public ResultModel<List<People>> getAllName(){
        List<People> listName= collectorService.getAllName();
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, listName, "全查人名成功");
    }

    /**
     * 删除人员信息
     * @param collector collectorId:需要前端传递
     * @return
     */
    @PostMapping("/deleteCollector.do")
    public ResultModel<Collector> deleteCollector(Collector collector){
        collectorService.deleteCollector(collector.getCollectorId());
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, collector, "删除检测人员成功");
    }

    /**
     * 添加采集人员信息
     * @param collector
     * @return
     */
    @PostMapping("/addCollector.do")
    public ResultModel<Collector> addCollector(@RequestBody Collector collector) throws BusinessException {

        String password = collector.getPassword();
        String signKeyMd5 = MD5.encrypt(password);
        collector.setPassword(signKeyMd5);

        collector.setRegistTime(new Date());
        collectorService.addCollector(collector);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, collector, "添加检测人员成功");
    }

    /**
     * 修改采集人员信息
     * @param collector
     * @return
     */
    @PostMapping("/updateCollector.do")
    public ResultModel<Collector> updateCollector(@RequestBody Collector collector) throws BusinessException {

        //密码进行MD5加密后查询登录
        String passwordMd5 = MD5.encrypt(collector.getPassword());
        System.out.println(passwordMd5);
        collector.setPassword(passwordMd5);

        collectorService.updateCollector(collector);
        return new ResultModel<>(ResultCodeEnum.SUCCESS, collector, "修改检测人员成功");
    }


    /**
     * 根据name和idcard进行模糊查询
     * @param collector  name和idcard
     * @return
     */
    @PostMapping("getLikeNameIdCard.do")
    ResultModel<List<Collector>> getLikeNameIdCard(@RequestBody Collector collector){
        List<Collector> likeNameIdCard = collectorService.getLikeNameIdCard(collector.getName());
        return new ResultModel<>(ResultCodeEnum.SUCCESS,likeNameIdCard,"条件查询成功");
    }
}
