package com.example.aop.common.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Order(1)
@Component
public class StopWatchAop {

    @Around("@annotation(com.example.aop.common.annotation.StopWatchAnnotation)")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(joinPoint.getSignature().getName());
        Object result = joinPoint.proceed(); // 실제 메서드 수행
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        return result;
    }

}
