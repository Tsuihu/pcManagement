package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.PeopleResultBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface PeopleService {

    PageResultBo<People> getAllPeople(PageRequestBo pageRequestBo);

    void addPeople(People people) throws BusinessException;

    void deletePeopleById(Integer peopleId);

    void updatePeopleById(People people) throws BusinessException;


    List<People> getLikeNameIdCard(String name);
}
