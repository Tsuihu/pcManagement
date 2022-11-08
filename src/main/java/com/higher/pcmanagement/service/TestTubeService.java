package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Testtube;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.TesttubeBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface TestTubeService {
    PageResultBo<TesttubeBo> getPageTube(PageRequestBo model);



    void addTestTube(String testtubeCode,Integer collectType,Integer boxId,Date openTime) throws BusinessException;


    //修改试管
    void updateTestTube(Testtube testtube) throws BusinessException;

//    删除试管
    void deleteTube(@Param("testtubeId") Integer testtubeId);
}
