package com.ljq.demo.general;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author junqiang.lu
 */
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.ljq.demo.general.*"})
@MapperScan(basePackages = {"com.ljq.demo.general.dao"})
public class DemoGeneralBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGeneralBootApplication.class, args);
    }

}
