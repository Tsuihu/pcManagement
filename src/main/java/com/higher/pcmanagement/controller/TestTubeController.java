package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.dao.TestTubeDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.Testtube;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.TesttubeBo;
import com.higher.pcmanagement.service.TestTubeService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/testtube")
public class TestTubeController {

    @Autowired
    TestTubeService testTubeService;

    @Autowired
    TestTubeDao testTubeDao;

    /**
     * 分页查询
     * @param model pageIndex   pageSize
     * @return
     */
    @PostMapping("getPage.do")
    ResultModel<PageResultBo<TesttubeBo>> getPageTube(@RequestBody PageRequestBo model){
        PageResultBo<TesttubeBo> pagePoint = testTubeService.getPageTube(model);

        return new ResultModel<>(ResultCodeEnum.SUCCESS,pagePoint,"分页查询成功");
    }


    /**
     * 添加试管
     * @param testtubeBo 参数:testtubeCode,collectType,boxCode
     * @return
     * @throws BusinessException
     */
    @PostMapping("addTestTube.do")
    ResultModel<TesttubeBo> addTestTube(@RequestBody TesttubeBo testtubeBo) throws BusinessException {
        String boxCode = testtubeBo.getBoxCode();
        Integer boxId = testTubeDao.getBoxIdByCode(boxCode);
        if (boxId==null){
            throw new BusinessException("转运箱编码不存在，请先添加转运箱", ResultCodeEnum.ERROR);
        }else {
            testTubeService.addTestTube(testtubeBo.getTesttubeCode(),testtubeBo.getCollectType(),boxId,new Date());
            return new ResultModel<>(ResultCodeEnum.SUCCESS,testtubeBo,"试管添加成功");
        }
    }


    /**
     * 修改试管
     * @param testtubeBo    testtubeCode,collectType,boxCode,status,testResult
     * @return
     * @throws BusinessException
     */
    @PostMapping("updateTestTube.do")
    ResultModel<TesttubeBo> updateTestTube(@RequestBody TesttubeBo testtubeBo) throws BusinessException {

        String boxCode = testtubeBo.getBoxCode();
        Integer boxId = testTubeDao.getBoxIdByCode(boxCode);
        if (boxId==null){
            throw new BusinessException("转运箱编码不存在，请先添加转运箱", ResultCodeEnum.ERROR);
        }else {
            testtubeBo.setBoxId(boxId);
            testTubeService.updateTestTube(testtubeBo);
            return new ResultModel<>(ResultCodeEnum.SUCCESS,testtubeBo,"试管修改成功");
        }
    }


    /**
     * 删除操作,不仅删除testtube表中的信息,还会删除sample表中的信息,请谨慎删除
     * @param testtubeId
     * @return
     */
    @PostMapping("deleteTube.do")
    ResultModel<String> deleteTube(Integer testtubeId){
        testTubeService.deleteTube(testtubeId);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,"删除成功");
    }


}
