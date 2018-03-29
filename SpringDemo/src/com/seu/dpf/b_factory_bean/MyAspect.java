package com.seu.dpf.b_factory_bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
 * 切面类确定通知，需要实现不同的接口
 */
public class MyAspect implements MethodInterceptor{
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		System.out.println("start");
		
		Object obj = mi.proceed();
		
		System.out.println("end");
		return obj;
	}
}
