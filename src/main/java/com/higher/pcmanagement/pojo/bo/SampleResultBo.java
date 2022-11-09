package com.higher.pcmanagement.pojo.bo;

import com.higher.pcmanagement.pojo.Sample;
import lombok.Data;

@Data
public class SampleResultBo extends Sample {
    private  String testResult;
    private String testtubeCode;
}
