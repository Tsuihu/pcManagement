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
    public List<SampleResultBo> getResultByPeopleId(Integer peopleId) {
        List<SampleResultBo> resultByPeopleId = sampleDao.getResultByPeopleId(peopleId);
        return resultByPeopleId;
    }

    @Override
    public void addSample(SampleResultBo sampleResultBo) throws BusinessException {
        int tubeId = sampleDao.getTubeIdByTubeCode(sampleResultBo.getTesttubeCode());
        sampleResultBo.setTesttubeId(tubeId);
        if (sampleDao.getCountByTubeCode(sampleResultBo.getTesttubeCode())>0){
            sampleDao.addSample(sampleResultBo);
        }else {
            throw new BusinessException("该试管不存在", ResultCodeEnum.ERROR);
        }

    }
}
