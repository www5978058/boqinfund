package com.shyb.boqinfund;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.shyb.boqinfund.mapper")
@SpringBootApplication
public class BoQinFundApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoQinFundApplication.class, args);
    }

}
