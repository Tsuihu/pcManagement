package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.ManagerDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.ManagerService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

        if(managerDao.checkManagerIdCard(manager.getIdcard()) > 0) {
            throw new BusinessException("身份证已存在",ResultCodeEnum.ERROR);
        }else {
            if (managerDao.checkManagerRepeat(manager.getTel()) > 0) {
                throw new BusinessException("电话号码已存在", ResultCodeEnum.ERROR);
            } else {
                managerDao.addManager(manager);
            }
        }
    }

    @Override
    public PageResultBo<Manager> getPageManager(PageRequestBo model) {
        if (model.getPageIndex()< 0){
            model.setPageIndex(0);
        }
        List<Manager> list = managerDao.getPageManager(model);
        Integer count = managerDao.getPageManagerCount();
        PageResultBo<Manager> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }

    @Override
    public void deleteManager(Integer managerId) {
        managerDao.deleteManager(managerId);
    }

    /**
     * 根据管理员名字查询信息
     * @param name
     * @return
     */
    @Override
    public List<Manager> getLikeNameIdCard(String name) {
        List<Manager> likeNameIdCard = managerDao.getLikeNameIdCard(name);
        return likeNameIdCard;
    }

    /**
     * 修改管理员信息
     * @param managerId
     * @param name
     * @param idcard
     * @param tel
     * @param password
     * @throws BusinessException
     */
    @Override
    public void updateManager(Integer managerId, String name, String idcard, String tel, String password) throws BusinessException {
            managerDao.updateManager(managerId,name,idcard,tel,password);
    }
}
