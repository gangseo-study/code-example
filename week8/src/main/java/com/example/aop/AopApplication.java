package com.example.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * dependency에 spring-boot-starter-aop 가 추가 되어 있다면 @EnableAspectJAutoProxy를 사용할 필요 없음
 * AopAutoConfiguration에 의해 자동으로 활성화됨
 * https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/autoconfigure/aop/AopAutoConfiguration.html
 */
@SpringBootApplication
//@EnableAspectJAutoProxy
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

}
