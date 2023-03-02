package study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * @SpringBootApplication
 * - 어플리케이션의 시작점에 있는 이 어노테이션은 스프링의 다음 3가지 기능을 활성화 한다.
 * 1. @ComponentScan: @Component 어노테이션을 갖는 객체를 찾아 bean에 등록한다.
 * 2. @SpringBootConfiguration: 추가로 생성한 스프링 부트의 설정을 나타내는 @Configuration 어노테이션을 찾아 bean에 등록한다.
 * 3. @EnableAutoConfiguration: 자동설정의 핵심 어노테이션으로 dependency에 추가한 jar 클래스패스에 등록된 클래스들을 bean에 등록한다.
 *
 */

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}