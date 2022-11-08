package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.TestTubeDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.Testtube;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.TesttubeBo;
import com.higher.pcmanagement.service.TestTubeService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TestTubeServiceImpl implements TestTubeService {

    @Autowired
    TestTubeDao testTubeDao;

    @Override
    public PageResultBo<TesttubeBo> getPageTube(PageRequestBo model) {
        if (model.getPageIndex()< 0){
            model.setPageIndex(0);
        }
        List<TesttubeBo> list = testTubeDao.getPageTube(model);
        Integer count = testTubeDao.getPageTubeCount();
        PageResultBo<TesttubeBo> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }

    @Override
    public void addTestTube(String testtubeCode, Integer collectType,
                            Integer boxId, Date openTime) throws BusinessException {
        Testtube tube = testTubeDao.getTubeByCode(testtubeCode);
        if (tube!=null){
            throw new BusinessException("试管编码已存在，请重新输入", ResultCodeEnum.ERROR);
        }

        testTubeDao.addTestTube(testtubeCode,collectType,boxId,openTime);
    }

    @Override
    public void updateTestTube(Testtube testtube) throws BusinessException {
//        根据获取的id获取数据库中的信息
        Testtube tube = testTubeDao.getTubeById(testtube.getTesttubeId());
//        获取数据库中的id
        Integer testtubeId = tube.getTesttubeId();
//        获取数据库中的状态
        Integer tubeStatus = tube.getStatus();
//        获取输入的状态信息
        Integer status = testtube.getStatus();

//        获取试管中的人员条数
        Integer peopleCount = testTubeDao.getCountByTubeId(testtube.getTesttubeId());
//        获取试管的类型
        Integer collectType = testtube.getCollectType();

//        获取输入框中编码的试管信息
        Testtube newtube = testTubeDao.getTubeByCode(testtube.getTesttubeCode());
        System.out.println(newtube);
//        获取输入框中编码的试管id
        Integer testtubeId1 = newtube.getTesttubeId();
        if (testtubeId!=testtubeId1 && newtube!=null){
            throw new BusinessException("试管编码已存在，请重新输入", ResultCodeEnum.ERROR);
        }else {

//        人数条不能大于试管类型
        if (peopleCount<=collectType){
            if (tubeStatus==status){
//                两状态相通，开封管时间不变
                testtube.setOpenTime(tube.getOpenTime());
                testtube.setCloseTime(tube.getCloseTime());
                testTubeDao.updateTestTube(testtube);
            } else if (tubeStatus != status && status==1) {
                testtube.setOpenTime(tube.getOpenTime());
                testtube.setCloseTime(new Date());
                testTubeDao.updateTestTube(testtube);
            }else if (tubeStatus != status && status==0){
                testtube.setOpenTime(tube.getOpenTime());
                testtube.setCloseTime(null);
                testTubeDao.updateTestTube(testtube);
            }
            else {
                throw new BusinessException("状态输入错误，请输入0或者1",ResultCodeEnum.ERROR);
            }
        }else {
            throw new BusinessException("试管中已采集"+peopleCount+"条信息，试管类型不能小于此数",ResultCodeEnum.ERROR);
        }
        }

    }


//    删除试管
    @Override
    public void deleteTube(Integer testtubeId) {
        testTubeDao.deleteTube(testtubeId);
    }
}
