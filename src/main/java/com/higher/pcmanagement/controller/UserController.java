package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.bo.LoginModel;
import com.higher.pcmanagement.service.ManagerService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import com.higher.pcmanagement.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
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
        Manager manager = managerService.login(model.getTel(), model.getPassword());
        //上面的方法只要不报错，就是调用成功，可以直接返回。
        return new ResultModel<>(ResultCodeEnum.SUCCESS, manager, "");
    }
    /**
     * 注册接口
     * @param manager
     * @return ResultModel<Manager>
     * @throws BusinessException
     */
    @PostMapping("registe.do")
    public ResultModel<Manager> registe(@RequestBody Manager manager) throws BusinessException {
        managerService.addManager(manager);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,manager,"");
    }
}
