package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.LoginModel;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.ManagerService;
import com.higher.pcmanagement.util.MD5;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import com.higher.pcmanagement.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;

    /**
     * 登录接口
     * @param model(tel:电话，password:密码)
     * @return ResultModel<Manager>
     * @throws BusinessException
     */
    @PostMapping("login.do")
    public ResultModel<Manager> login(@RequestBody LoginModel model) throws BusinessException {

        //密码进行MD5加密后查询登录
        String passwordMd5 = MD5.encrypt(model.getPassword());
        System.out.println(passwordMd5);

        Manager manager = managerService.login(model.getTel(),passwordMd5);
        //上面的方法只要不报错，就是调用成功，可以直接返回。
        return new ResultModel<>(ResultCodeEnum.SUCCESS, manager, "登录成功");
    }
    /**
     * 注册接口
     * @param manager
     * @return ResultModel<Manager>
     * @throws BusinessException
     */
    @PostMapping("registe.do")
    public ResultModel<Manager> registe(@RequestBody Manager manager) throws BusinessException {

        //MD5加密
        String password = manager.getPassword();
        String signKeyMd5 = MD5.encrypt(password);
        manager.setPassword(signKeyMd5);

        manager.setRegistTime(new Date());
        managerService.addManager(manager);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,manager,"注册成功");
    }


    @PostMapping("/getPage.do")
    ResultModel<PageResultBo<Manager>> getPageManager(@RequestBody PageRequestBo model){
        PageResultBo<Manager> pageManager = managerService.getPageManager(model);

        return new ResultModel<>(ResultCodeEnum.SUCCESS,pageManager,"分页查询成功");
    }


    @PostMapping("/deleteManager.do")
    ResultModel<String> deleteManager(Integer managerId){
        managerService.deleteManager(managerId);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,"信息删除成功");
    }


    /**
     * 根据name和idcard进行模糊查询
     * @param manager  name和idcard
     * @return
     */
    @PostMapping("getLikeNameIdCard.do")
    ResultModel<List<Manager>> getLikeNameIdCard(@RequestBody Manager manager){
        List<Manager> likeNameIdCard = managerService.getLikeNameIdCard(manager.getName(), manager.getIdcard());
        return new ResultModel<>(ResultCodeEnum.SUCCESS,likeNameIdCard,"条件查询成功");
    }


    @PostMapping("updateManager.do")
    ResultModel<Manager> updateManager(@RequestBody Manager manager) throws BusinessException {
        String password = MD5.encrypt(manager.getPassword());

        managerService.updateManager(manager.getManagerId(), manager.getName(),
                        manager.getIdcard(), manager.getTel(), password);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,"修改信息成功");
    }



}
