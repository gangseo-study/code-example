package com.example.aop.config;

import com.example.aop.config.interceptor.InterceptorTest;
import com.example.aop.config.interceptor.InterceptorTest2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer를 구현하여 Spring WebApplicationContext부분을 커스터마이징 할 수 있게 한다.
 */
@Configuration
public class WebMcvConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new InterceptorTest()).addPathPatterns("/board/**");
        registry.addInterceptor(new InterceptorTest2()).excludePathPatterns("/calc/advice/**");
    }
}
