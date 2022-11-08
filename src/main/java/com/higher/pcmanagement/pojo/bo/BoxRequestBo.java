package com.higher.pcmanagement.pojo.bo;

import com.higher.pcmanagement.pojo.Box;
import lombok.Data;

@Data
public class BoxRequestBo extends Box {
    private String name;
    private String pointName;
}
