package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PeopleResultBo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PeopleDao {
    List<PeopleResultBo> getAllPeople(PageRequestBo pageRequestBo);
    int getPagePeopleCount();
}
