package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.bo.*;
import com.higher.pcmanagement.service.ManagerService;
import com.higher.pcmanagement.service.PeopleService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    /**
     * 获取所有人员信息
     * @param pageRequestBo pageIndex，pageSize
     * @return
     */
    @PostMapping("/getAllPeople.do")
    public ResultModel<PageResultBo<People>> getAllPeople(@RequestBody PageRequestBo pageRequestBo){
        PageResultBo<People> listPeople= peopleService.getAllPeople(pageRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, listPeople, "分页查询所有人信息成功");
    }

    /**
     * 添加人员信息
     * @param people name，idcard，tel
     * @return
     * @throws BusinessException
     */
    @PostMapping("/addPeople.do")
    public ResultModel<People> addPeople(@RequestBody People people) throws BusinessException {
        people.setCreateTime(new Date());
        peopleService.addPeople(people);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, people, "添加人员信息成功");
    }

    /**
     * 删除人员信息
     * @param people peopleId
     * @return
     * @throws BusinessException
     */
    @PostMapping("/deletePeopleById.do")
    public ResultModel<People> deletePeopleById(People people) throws BusinessException {
        peopleService.deletePeopleById(people.getPeopleId());
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, people, "删除人员信息成功");
    }

    /**
     * 修改人员信息
     * @param people peopleId，name，idcard，tel
     * @return
     * @throws BusinessException
     */
    @PostMapping("/updatePeopleById.do")
    public ResultModel<People> updatePeopleById(@RequestBody People people) throws BusinessException {
        peopleService.updatePeopleById(people);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, people, "修改人员信息成功");
    }

}
