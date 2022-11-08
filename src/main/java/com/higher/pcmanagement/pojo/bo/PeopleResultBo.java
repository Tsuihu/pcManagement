package com.higher.pcmanagement.pojo.bo;

import com.higher.pcmanagement.pojo.People;
import lombok.Data;

@Data
public class PeopleResultBo extends People {
    private String testResult;
    private String testtubeCode;
}
