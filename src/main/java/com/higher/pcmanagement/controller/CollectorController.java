package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.CollectorService;
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

    @PostMapping("/getPageCollector.do")
    public ResultModel<PageResultBo<Collector>> getPageCollector(@RequestBody PageRequestBo pageRequestBo){
        PageResultBo<Collector> listCollector= collectorService.getPageCollector(pageRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, listCollector, "");
    }

    @PostMapping("/deleteCollector.do")
    public ResultModel<Collector> deleteCollector(Collector collector){
        collectorService.deleteCollector(collector.getCollectorId());
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, collector, "");
    }

    @PostMapping("/addCollector.do")
    public ResultModel<Collector> addCollector(@RequestBody Collector collector){
        collector.setRegistTime(new Date());
        collectorService.addCollector(collector);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, collector, "");
    }

    @PostMapping("/updateCollector.do")
    public ResultModel<Collector> updateCollector(@RequestBody Collector collector){
        collectorService.updateCollector(collector);
        return new ResultModel<>(ResultCodeEnum.SUCCESS, collector, "");
    }
}
