package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.BoxRequestBo;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface BoxService {

    PageResultBo<BoxRequestBo> getPageBox(PageRequestBo pageRequestBo);

    void deleteBox(Integer boxId);

    void addBox(BoxRequestBo boxRequestBo) throws BusinessException;

    void updateBox(BoxRequestBo boxRequestBo) throws BusinessException;


    List<Box> getAllBoxCode();

}
