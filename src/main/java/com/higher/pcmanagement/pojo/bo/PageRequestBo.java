package com.higher.pcmanagement.pojo.bo;

import lombok.Data;

@Data
public class PageRequestBo {
    private  Integer pageIndex;
    private  Integer pageSize;

    public Integer getRowBegin() {
        return (pageIndex-1)*pageSize;
    }
}
