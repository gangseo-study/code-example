package com.example.aop.common.advice;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * - @Aspect: 클래스가 횡단 관심 (부가 기능) 클래스임을 명시한다.
 * - @Order: 실행 순서를 정의한다.
 * - Advisor: 하나 이상의 Pointcut과 Advice를 가지는 것
 */
@Aspect
@Order(2)
@RequiredArgsConstructor
@Component
public class MathCalcAop {

    private final HashMap<String, Map<Long, Object>> resultMap; // 결과값을 저장할 Map

    /**
     * - @Pointcut: 부가 기능이 적용될 JointPoint 들을 정의 한다.
     * JointPoint란 프로그램 실행 중 한 지점을 말한다,
     * Spring AOP에서는 언제나 메서드가 수행될 때를 의미한다.
     * Pointcut이 적용된 method는 언제나 비어있는 body를 가져야 한다.
     * --------------------------------------------------------------
     * 표현식 연결 연산자
     * (*: 모두)
     * (..: 0개 이상)
     * (&&: and)
     * (|| or)
     */
    @Pointcut("execution(* com.example..*.service.AdviceCalcService*.*(..))")
    private void calc() {}


    /**
     * - @Around: 가장 강력한 종류의 Advice로 메서드 호출 전,후로 동작을 정의하고, 흐름을 제어할 수 있다.
     * Advice란 특정 Joinpoint에서 취해지는 Action이다.
     */
    @Around("calc()")
    private Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long num = (Long) joinPoint.getArgs()[0];
        String signatureName = joinPoint.getSignature().getName();

        // 호출 메서드 이름과 인자로 넘어온 num을 Key로 결과값을 저장할 Map을 구상한다.
        if (resultMap.containsKey(signatureName)){
            var map = resultMap.get(signatureName);
            System.out.println("get from map");
            if (map.containsKey(num)) return map.get(num);
        }

        Object result = joinPoint.proceed(); // 실제 메서드 수행

        resultMap.put(signatureName, Collections.singletonMap(num, result));

        return result;
    }

    /**
     * 메서드 호출 전에 일어나는 동작을 정의한다.
     */
    @Before("calc()")
    private void before(JoinPoint joinPoint) {
        System.out.println("@Before: 실행 메서드: " + joinPoint.getSignature().getName() + ", 인자: " + joinPoint.getArgs()[0]);
    }

    /**
     * 메서드 호출 후에 일어나는 동작을 정의한다.
     */
    @After("calc()")
    private void after(JoinPoint joinPoint) {
        System.out.println("@After: 실행 메서드: " + joinPoint.getSignature().getName() + " 종료");
    }

    /**
     * 메서드가 호출 후에 일어나는 동작을 정의하며, return 값과 함께 사용할 수 있다
     */
    @AfterReturning(value = "execution(* com.example.aop.service.AdviceCalcService.*(..))", returning = "returnValue")
    private void afterReturning(JoinPoint joinPoint, Object returnValue) {
        System.out.println("@AfterReturning: 실행 메서드: " + joinPoint.getSignature().getName() + " 종료, 결과값: " + returnValue);
    }
}
