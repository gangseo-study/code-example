package study.helper;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * - @Aspect: 해당 클래스가 spring aop에 반영된다는 것을 알린다.
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * - @Around: 타겟 메서드의 실행 전 후로 이루어지는 메서드를 정의한다.
     */
    @Before("execution(public * study.controller..*(..))")
    public void loggingController(JoinPoint joinPoint) {
        try {
            log.info("{} - {} called", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
