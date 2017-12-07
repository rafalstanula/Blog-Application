package com.stanula.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutDefinitions {
    @Pointcut("execution(public * com.stanula.services.PostsService.getPosts())")
    public void getPostsServiceMethodPointcut() {
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void serviceMethodsPointcut() {
    }

    @Pointcut("within(com.stanula.repositories.*)")
    public void repositoryPackagePointcut() {
    }

    @Pointcut("execution(* com.stanula.controller.PostController.*(..))")
    public void postsControllerRequestHandlerPointcut() {
    }
}
