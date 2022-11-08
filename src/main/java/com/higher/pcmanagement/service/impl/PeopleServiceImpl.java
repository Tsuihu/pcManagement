package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.PeopleDao;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.PeopleResultBo;
import com.higher.pcmanagement.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    PeopleDao peopleDao;

    @Override
    public PageResultBo<PeopleResultBo> getAllPeople(PageRequestBo pageRequestBo) {
        if(pageRequestBo.getPageIndex()<0){
            pageRequestBo.setPageIndex(0);
        }
        List<PeopleResultBo> list = peopleDao.getAllPeople(pageRequestBo);
        Integer count = peopleDao.getPagePeopleCount();
        PageResultBo<PeopleResultBo> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }
}
