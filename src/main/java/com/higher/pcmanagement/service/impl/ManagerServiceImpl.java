package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.ManagerDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.service.ManagerService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Service
@Component
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerDao managerDao;

    /**
     * 登录
     * @param tel
     * @param password
     * @return manager
     * @throws BusinessException
     */
    @Override
    public Manager login(String tel, String password) throws BusinessException {
        Manager manager = managerDao.getManagerByTel(tel);
        if(manager == null){
            throw new BusinessException("手机号不存在", ResultCodeEnum.LOGIN_ERROR);
        }
        if(manager.getPassword()!=null&&manager.getPassword().equals(password)){
            return manager;
        }
        throw new BusinessException("密码错误",ResultCodeEnum.LOGIN_ERROR);
    }

    /**
     * 注册
     * @param manager
     * @throws BusinessException
     */
    @Override
    public void addManager(Manager manager) throws BusinessException {
        if(managerDao.checkManagerRepeat(manager.getTel()) > 0) {
            throw new BusinessException("手机号已存在",ResultCodeEnum.ERROR);
        }else {
            managerDao.addManager(manager);
        }
    }
}
