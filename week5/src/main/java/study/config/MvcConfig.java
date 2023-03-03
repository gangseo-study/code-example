package study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 1. @Configuration: 스프링의 설정을 담당하는 클래스임을 알린다.
 * - 외부 라이브러리나 혹은 내장 클래스를 Bean으로 등록할 때 @Bean 어노테이션과 함께 사용한다.
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 1. @Bean: @Configuration 어노테이션으로 선언된 클래스 안의 메소드 위에 정의한다.
     * - 이 메서드가 반환한 객체가 빈으로 등록된다.
     * - 보통 개발자가 직접 만든 클래스가 아닌 외부 라이브러리의 클래스를 bean으로 등록할 때 사용한다.
     * - (@Component 어노테이션은 개발자가 직접 정의한 클래스에 사용)
     */
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".html");
        return resolver;
    }

}
