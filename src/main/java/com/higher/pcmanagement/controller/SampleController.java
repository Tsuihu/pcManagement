package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Sample;
import com.higher.pcmanagement.pojo.bo.SampleResultBo;
import com.higher.pcmanagement.service.SampleService;
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
@RequestMapping("/sample")
public class SampleController {
    @Autowired
    SampleService sampleService;

    /**
     * 查询结果
     * @param sampleResultBo peopleId
     * @return
     * @throws BusinessException
     */
    @PostMapping("/getResultByPeopleId.do")
    public ResultModel<List<SampleResultBo>> getResultByPeopleId(SampleResultBo sampleResultBo) throws BusinessException {
        List<SampleResultBo> list=sampleService.getResultByPeopleId(sampleResultBo.getPeopleId());
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, list, "查询成功");
    }

    /**
     * 添加检测信息
     * @param sampleResultBo peopleId，testtubeCode
     * @return
     * @throws BusinessException
     */
    @PostMapping("/addSample.do")
    public ResultModel<Sample> addSample(@RequestBody SampleResultBo sampleResultBo) throws BusinessException {
        sampleResultBo.setCollectTime(new Date());
        sampleService.addSample(sampleResultBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, sampleResultBo, "添加成功");
    }


}
