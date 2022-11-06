package com.higher.pcmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.higher.pcmanagement.dao")
public class PcManagementAppliction {

    public static void main(String[] args) {
        SpringApplication.run(PcManagementAppliction.class, args);
    }

}
