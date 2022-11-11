package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PeopleResultBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PeopleDao {
    List<People> getAllPeople(PageRequestBo pageRequestBo);
    int getPagePeopleCount();

    void addPeople(People people);
    void deletePeopleById(@Param("peopleId")Integer peopleId);
    void updatePeopleById(People people);

    int getCountByIdcard(@Param("idcard") String idcard);
    int  getCountBytel(@Param("tel") String tel);
    People getOneByPeopleId(@Param("peopleId")Integer peopleId);


    List<People> getLikeNameIdCard(@Param("name") String name);

}
