package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface BoxService {

    PageResultBo<Box> getPageBox(PageRequestBo pageRequestBo);

    void deleteBox(Integer boxId);

    void addBox(Box box) throws BusinessException;

    void updateBox(Box box);
}
