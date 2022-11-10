package com.higher.pcmanagement.dao;


import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.Testtube;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.TesttubeBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface TestTubeDao {
    //分页查询，TesttubeBo继承Testtube，添加了boxCode字段，方便展示
    List<TesttubeBo> getPageTube(PageRequestBo model);

    List<Testtube> getAllTubeCode();

    //查询每页信息的行数
    Integer getPageTubeCount();

    //添加试管,参数，testtubeCode,collectType,boxId(这个需要先输入boxCode单查boxId是否存在)
    void addTestTube(@Param("testtubeCode") String testtubeCode, @Param("collectType")
                        Integer collectType, @Param("boxId")Integer boxId,@Param("openTime") Date openTime);

    //根据获取到的testtubeCode判断试管是否存在,返回Testtube信息
    Testtube getTubeByCode(@Param("testtubeCode") String testtubeCode);

    //根据boxCode单查boxId
    Integer getBoxIdByCode(@Param("boxCode") String boxCode);

    //修改试管
    void updateTestTube(Testtube testtube);
    //根据试管Id获取试管里的采集人数
    Integer getCountByTubeId(@Param("testtubeId") Integer testtubeId);

//    根据testtubeId获取Testtube信息所有信息
    Testtube getTubeById(@Param("testtubeId") Integer testtubeId);

//    删除操作testtube表
    void deleteTube(@Param("testtubeId") Integer testtubeId);

    void deleteSample(@Param("testtubeId") Integer testtubeId);


    List<Testtube> getLikeCode(@Param("testtubeCode")String testtubeCode);
}
