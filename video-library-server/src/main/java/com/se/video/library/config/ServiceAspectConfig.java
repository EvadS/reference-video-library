package com.se.video.library.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
//@Aspect
public class ServiceAspectConfig {

//    @Around("execution(* com.se.video.library ..*(..))")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//
//        log.info(String.format("Execution. Class:{}, method:{}",className,methodName ));
//
//        Object result = joinPoint.proceed();
//        return result;
//
//    }
}
