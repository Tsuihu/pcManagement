package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.bo.LoginModel;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.PeopleResultBo;
import com.higher.pcmanagement.service.ManagerService;
import com.higher.pcmanagement.service.PeopleService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    @PostMapping("/getAllPeople.do")
    public ResultModel<PageResultBo<PeopleResultBo>> getAllPeople(@RequestBody PageRequestBo pageRequestBo){
        PageResultBo<PeopleResultBo> listPeople= peopleService.getAllPeople(pageRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, listPeople, "");
    }
}
