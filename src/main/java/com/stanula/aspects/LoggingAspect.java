package com.stanula.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggingAspect {

    @Before("PointcutDefinitions.serviceMethodsPointcut()")
    public void logMetodSignature(JoinPoint joinPoint) {
        System.out.println("Method: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "PointcutDefinitions.getPostsServiceMethodPointcut()", returning = "returnedValue")
    public void logReturnedValue(Object returnedValue) {
        System.out.println("Returned Value: " + returnedValue);
    }

    @Around("PointcutDefinitions.repositoryPackagePointcut()")
    public Object logRepositoryMethodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(proceedingJoinPoint.getSignature() + " - Starting... ");
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println(proceedingJoinPoint.getSignature() + " - Finished after " + (endTime - startTime) + "ms");
        return result;
    }

    @Before("PointcutDefinitions.postsControllerRequestHandlerPointcut() && args(model)")
    public void logMethodsArguments(JoinPoint joinPoint, Model model) {
        System.out.println("PostsController before aspect.");
        System.out.println("\tMethod invoked with argument: " + model + " (or using getArgs() method: "
                + Arrays.deepToString(joinPoint.getArgs()) + ")");
        System.out.println("\tMethod annotations: " + getAnnotationNames(joinPoint));
    }

    private List<? extends Annotation> getAnnotationNames(JoinPoint joinPoint) {
        return Arrays.asList(castToMethodSignature(joinPoint).getMethod().getAnnotations());
    }

    private MethodSignature castToMethodSignature(JoinPoint joinPoint) {
        return (MethodSignature) joinPoint.getSignature();
    }

}
