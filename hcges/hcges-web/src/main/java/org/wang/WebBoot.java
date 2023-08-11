package org.wang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.wang.mapper")
public class WebBoot {
    public static void main(String[] args) {
        SpringApplication.run(WebBoot.class,args);
    }
}
