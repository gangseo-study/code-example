package com.example.aop.service;

import com.example.aop.common.annotation.StopWatchAnnotation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class AdviceCalcService {

    @StopWatchAnnotation
    public BigInteger calcFactorial(Long n) {
        BigInteger tempResult = BigInteger.ONE;

        for (long i = n; i > 0; i--) {
            tempResult = tempResult.multiply(new BigInteger((Long.toString(i))));
        }
        return tempResult;
    }

    public BigDecimal getPIValue(Long decimal) {
        BigDecimal pi = BigDecimal.valueOf(0);
        for (long i = decimal; i > 0; i--) {
            pi = pi.add(BigDecimal.valueOf(Math.pow(-1, i + 1) / (2 * i - 1)));
            if (i == 1) {
                pi = pi.multiply(BigDecimal.valueOf(4));
                break;
            }
        }
        return pi;
    }

}
