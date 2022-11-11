package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.PeopleDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.pojo.bo.PeopleResultBo;
import com.higher.pcmanagement.service.PeopleService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    PeopleDao peopleDao;
//全查
    @Override
    public PageResultBo<People> getAllPeople(PageRequestBo pageRequestBo) {
        if(pageRequestBo.getPageIndex()<0){
            pageRequestBo.setPageIndex(0);
        }
        List<People> list = peopleDao.getAllPeople(pageRequestBo);
        Integer count = peopleDao.getPagePeopleCount();
        PageResultBo<People> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }
//添加
    @Override
    public void addPeople(People people) throws BusinessException {
        //        对身份证的长度进行一个长度要求，要求18位
        if (people.getIdcard().length()==18){
//            对手机后进行一个要求，要求11位
            if (people.getTel().length()==11){
                if (peopleDao.getCountByIdcard(people.getIdcard())==0){
                        peopleDao.addPeople(people);
                }else {
                    throw new BusinessException("该身份证已存在，请重试", ResultCodeEnum.ERROR);
                }
            }else {
                throw new BusinessException("请输入正确的手机号", ResultCodeEnum.ERROR);
            }
        }else {
            throw new BusinessException("请输入正确的身份证格式", ResultCodeEnum.ERROR);
        }
    }

//删除
    @Override
    public void deletePeopleById(Integer peopleId) {
        peopleDao.deletePeopleById(peopleId);
    }

//修改
    @Override
    public void updatePeopleById(People people) throws BusinessException {
//        通过id查询未改之前的信息
        People peopleOne = peopleDao.getOneByPeopleId(people.getPeopleId());
//        对身份证的长度进行一个长度要求，要求18位
        if (people.getIdcard().length()==18){
//            对手机后进行一个要求，要求11位
            if (people.getTel().length()==11){
//              进行判断，如果未改之前的身份证与改之后的身份证一样。
//              或者，未改之前的身份证与改之后的身份证不一样，并且，改制后的身份证再数据库中不存在，则进行修改，下面判断手机号同理
                if (peopleOne.getIdcard().equals(people.getIdcard()) ||
                        (!peopleOne.getIdcard().equals(people.getIdcard()) && peopleDao.getCountByIdcard(people.getIdcard())==0)){
//                    if (peopleOne.getTel().equals(people.getTel()) ||
//                            (!peopleOne.getTel().equals(people.getTel()) && peopleDao.getCountBytel(people.getTel())==0) ){

                        peopleDao.updatePeopleById(people);

//                    }else {
//                        throw new BusinessException("该手机号已存在，请重试", ResultCodeEnum.ERROR);
//                    }
                }else {
                    throw new BusinessException("该身份证已存在，请重试", ResultCodeEnum.ERROR);
                }
            }else {
                throw new BusinessException("请输入正确的手机号", ResultCodeEnum.ERROR);
            }
        }else {
            throw new BusinessException("请输入正确的身份证格式", ResultCodeEnum.ERROR);
        }

    }


    @Override
    public List<People> getLikeNameIdCard(String name) {
        List<People> likeNameIdCard = peopleDao.getLikeNameIdCard(name);
        return likeNameIdCard;
    }
}
