package org.hsweb.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@ComponentScan("org.hsweb.demo")
@MapperScan("org.hsweb.demo.dao")
public class Run {
    @Transactional
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Run.class, args);
    }
}
