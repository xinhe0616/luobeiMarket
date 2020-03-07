package com.mao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.mao.dao")
@SpringBootApplication
public class LuobeimarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuobeimarketApplication.class, args);
    }

}
