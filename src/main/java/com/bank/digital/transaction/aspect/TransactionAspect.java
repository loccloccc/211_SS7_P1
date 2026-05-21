package com.bank.digital.transaction.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {
    @Around("execution(* com.bank.digital.transaction.Service.TransactionService.*(..))")
    public Object logExecutionTime(org.aspectj.lang.ProceedingJoinPoint joinPoint) throws Throwable {
        Object rs;
        long startTime = System.currentTimeMillis();
        rs = joinPoint.proceed();
        long excutionTime = System.currentTimeMillis() - startTime;
        System.out.println("ASPECT: Thời gian thực thi phương thức " + joinPoint.getSignature() + " là " + excutionTime + "ms");
        return rs;
        }
}
