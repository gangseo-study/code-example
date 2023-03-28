package com.example.aop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class CalcService {

    private final HashMap<Long, BigInteger> factorialMap;
    private final HashMap<Long, BigDecimal> piMap;

    /*
     * 팩토리얼 계산 메서드
     * 같은 파라미터가 들어오면 무조건 같은 결과를 뱉으므로, 계산 결과를 저장해 놓음
     * 다음에 같은 파라미터가 다시 들어오면 계산이 아닌 Map에서 반환
     */
    public BigInteger calcFactorial(Long n) {
        if (factorialMap.containsKey(n)) return factorialMap.get(n); // 부가적 기능

        // 핵심 기능
        BigInteger result = BigInteger.ONE;
        for (long i = n; i > 0; i--) {
            result = result.multiply(new BigInteger((Long.toString(i))));
        }
        System.out.println("temp result:" +result);
        //

        factorialMap.put(n, result);  // 부가적 기능
        return result;
    }

    /*
     * 파이 계산 메서드
     * 같은 파라미터가 들어오면 무조건 같은 결과를 뱉으므로, 계산 결과를 저장해 놓음
     * 다음에 같은 파라미터가 다시 들어오면 계산이 아닌 Map에서 반환
     */
    public BigDecimal getPIValue(Long decimal) {
        if (piMap.containsKey(decimal)) return piMap.get(decimal); // 부가적 기능

        // 핵심 기능
        BigDecimal pi = BigDecimal.valueOf(0);
        for (long i = decimal; i > 0; i--) {
            pi = pi.add(BigDecimal.valueOf(Math.pow(-1, i + 1) / (2 * i - 1)));
            if (i == 1) {
                pi = pi.multiply(BigDecimal.valueOf(4));
                break;
            }
        }
        System.out.println(pi);
        //

        piMap.put(decimal, pi); // 부가적 기능
        return pi;
    }
}
