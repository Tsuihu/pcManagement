package com.higher.pcmanagement.exception;

import com.higher.pcmanagement.util.ResultCodeEnum;
import lombok.Data;

@Data
public class BusinessException extends  Exception{
    private ResultCodeEnum resultCode;

    public BusinessException(String message, ResultCodeEnum resultCode) {
        super(message);
        this.resultCode = resultCode;
    }
}
