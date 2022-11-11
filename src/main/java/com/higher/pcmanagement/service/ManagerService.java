package com.higher.pcmanagement.service;

import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface ManagerService {
    public Manager login(String tel, String password) throws BusinessException;
    public void  addManager(Manager manager) throws BusinessException;


    PageResultBo<Manager> getPageManager(PageRequestBo model);


    void deleteManager(Integer managerId);

    List<Manager> getLikeNameIdCard(String name);


    void updateManager(Integer managerId,String name,String idcard,String tel,String password) throws BusinessException;
}
