package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.SampleDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.bo.SampleResultBo;
import com.higher.pcmanagement.service.SampleService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public class SampleServiceImpl implements SampleService {
    @Autowired
    SampleDao sampleDao;


    @Override
    public List<SampleResultBo> getResultByPeopleId(Integer peopleId) throws BusinessException {
//        判断此人员是否存在sample表中
        if (sampleDao.getCountByPeopleId(peopleId)>0){
            List<SampleResultBo> resultByPeopleId = sampleDao.getResultByPeopleId(peopleId);
            return resultByPeopleId;
        }else {
            throw new BusinessException("该人员尚未检测，请重试", ResultCodeEnum.ERROR);
        }
    }

    @Override
    public void addSample(SampleResultBo sampleResultBo) throws BusinessException {

        if (sampleDao.getCountByTubeCode(sampleResultBo.getTesttubeCode())>0){
//        获取试管的Id
            int testtubeId = sampleDao.getTubeIdByTubeCode(sampleResultBo.getTesttubeCode());
            sampleResultBo.setTesttubeId(testtubeId);

//        获取试管类型和试管Id再sample表中的返回行数
            int tubetype = sampleDao.getTypeByTubeId(testtubeId);
            int count = sampleDao.getCountByTubeId(testtubeId);

//        通过试管id，判断此试管是开管还是封管
            if (sampleDao.getStatusByTubeId(testtubeId)==0){
//            对试管的类型和返回行数进行一个比较
                if(count<tubetype){
                    sampleDao.addSample(sampleResultBo);
                }else {
                    throw new BusinessException("该试管已满，无法继续添加", ResultCodeEnum.ERROR);
                }
            }else {
                throw new BusinessException("该试管已封，无法继续添加", ResultCodeEnum.ERROR);
            }
        }else {
            throw new BusinessException("该试管不存在", ResultCodeEnum.ERROR);
        }

    }
}
