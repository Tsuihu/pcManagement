package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface BoxDao {
    List<Box> getPageBox(PageRequestBo pageRequestBo);
    Integer getPageBoxCount();

    void deleteBox(@Param("boxId") Integer boxId);

    void addBox(Box box);

    void updateBox(Box box);
}
