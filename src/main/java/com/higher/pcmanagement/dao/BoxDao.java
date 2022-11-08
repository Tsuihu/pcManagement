package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.bo.BoxRequestBo;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface BoxDao {
    List<BoxRequestBo> getPageBox(PageRequestBo pageRequestBo);
    Integer getPageBoxCount();

    void deleteBox(@Param("boxId") Integer boxId);

    void addBox(BoxRequestBo boxRequestBo);
    int getCountByBoxcode(@Param("boxCode")String boxCode);
    int getcollectorIdByname(@Param("name")String name);
    int getCountByname(@Param("name")String name);
    int getpointIdByPointName(@Param("pointName")String pointName);
    int getCountByPointName(@Param("pointName")String pointName);


    void updateBox(Box box);
    String getStatusByboxCode(@Param("boxCode")String boxCode);
    Box getboxByBoxCode(@Param("boxCode")String boxCode);
}
