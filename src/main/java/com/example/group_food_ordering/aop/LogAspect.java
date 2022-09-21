package com.example.group_food_ordering.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Pointcut("execution(* com.example.group_food_ordering.controller.*.*(..))")
	public void pointcut() {}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		StringBuffer args = new StringBuffer();
		for(Object object : pjp.getArgs())
			args.append(object+" ");
		String methodName = pjp.getSignature().getName();
		
		long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		long spendTime = System.currentTimeMillis() - startTime;
		
		logger.debug("Args : " + args +", Method Name : " + methodName + ", Spend Time : " + spendTime + "ms");
		
		return result;
	}

}
