package com.q8888.springboot.mybatis.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.q8888.springboot.mybatis.server.mapper")//将项目中对应的mapper类的路径加进来就可以了
public class SpringbootMybatisServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisServerApplication.class, args);
    }

}

