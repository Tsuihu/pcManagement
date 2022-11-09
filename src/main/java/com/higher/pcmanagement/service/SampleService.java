package com.higher.pcmanagement.service;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.bo.SampleResultBo;
import org.springframework.stereotype.Component;

import java.util.List;

//@Service
@Component
public interface SampleService {

    List<SampleResultBo> getResultByPeopleId(Integer peopleId);

    void addSample(SampleResultBo sampleResultBo) throws BusinessException;
}
