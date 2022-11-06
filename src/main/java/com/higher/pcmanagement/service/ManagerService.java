package com.higher.pcmanagement.service;

import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.exception.BusinessException;
import org.springframework.stereotype.Component;

//@Service
@Component
public interface ManagerService {
    public Manager login(String tel, String password) throws BusinessException;
    public void  addManager(Manager manager) throws BusinessException;
}
