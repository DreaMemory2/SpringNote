package com.stalight.crystal.aop;

import com.stalight.crystal.CrystalMod;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Component
@Aspect
public class LoginAspects {

    @Pointcut("execution(public void com.stalight.crystal.service.impl.RegistryServiceImpl.registerBlocks())")
    public void commonExpression() {

    }

    @Before("commonExpression()")
    public void beforeAdvice() {
        CrystalMod.LOGGER.info("前置通知");
    }

    @AfterReturning("commonExpression()")
    public void afterAdvice() {
        CrystalMod.LOGGER.info("后置通知");
    }

    @Around("commonExpression()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        CrystalMod.LOGGER.info("前环绕");
        Object obj = joinPoint.proceed();
        CrystalMod.LOGGER.info("后环绕");
        return obj;
    }

    @AfterThrowing("commonExpression()")
    public void exceptionAdvice() {
        CrystalMod.LOGGER.info("异常通知");
    }

    @After("commonExpression()")
    public void finalAdvice() {
        CrystalMod.LOGGER.info("最终通知");
    }
}
