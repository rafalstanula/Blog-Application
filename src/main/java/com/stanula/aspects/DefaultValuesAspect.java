package com.stanula.aspects;

import com.stanula.domain.Post;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
public class DefaultValuesAspect {

    @Around("PointcutDefinitions.getPostsServiceMethodPointcut()")
    public Object defaultPostsIfExceptionThrown(ProceedingJoinPoint joinPoint) {

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            return Stream.of(Post.createPost("Default Author", "Default Post")).collect(Collectors.toList());
        }
    }
}
