package com.higher.pcmanagement.pojo.bo;

import lombok.Data;

import java.util.List;

@Data
public class PageResultBo<T> {
    private List<T> data;
    private Integer count;

    public PageResultBo(List<T> data, Integer count) {
        this.data = data;
        this.count = count;
    }
}
