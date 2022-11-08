package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.PeopleResultBo;
import org.springframework.stereotype.Component;

//@Service
@Component
public interface PeopleService {

    PageResultBo<PeopleResultBo> getAllPeople(PageRequestBo pageRequestBo);
}
