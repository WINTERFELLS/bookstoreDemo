package com.seu.dpf.a_proxy.b_cglib;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class MyBeanFactory {

	public static UserServiceImpl createService() {
		//1.目标类
		final UserServiceImpl userService = new UserServiceImpl();
		//2.切面类
		final MyAspect myAspect = new MyAspect();
		/*3.代理类,采用cglib,底层创建目标类子类
		 * intercept() 等效 jdk  invoke()
		 * 参数1、参数2、参数3：以invoke一样
		 * 参数4：methodProxy 方法的代理
		*/
		//核心类
		Enhancer enhancer = new Enhancer();
		//确定父类
		enhancer.setSuperclass(userService.getClass());
		//设置回调函数,等效jdk中的invocationHandler接口
		enhancer.setCallback(new MethodInterceptor() {
			
			@Override
			public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				
				myAspect.before();
				Object obj = method.invoke(userService, args);
				myAspect.after();
				return obj;
			}
		});
		UserServiceImpl proxyService = (UserServiceImpl) enhancer.create();
		return proxyService;
	}
}
