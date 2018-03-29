package com.seu.dpf.a_proxy.a_jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyBeanFactory {

	public static UserService createService() {
		//1.目标类
		final UserService userService = new UserServiceImpl();
		//2.切面类
		final MyAspect myAspect = new MyAspect();
		/*3.代理类,将目标类（切入点）和切面类（通知）结合，形成切面
		 * Proxy.newProxyInstance
		 * 	参数1：loader,类加载器，使用当前类：MyBeanFactory.class.getClassLoader()
		 * 							使用目标类：UserService.getClass().getClassLoader()
		 * 	参数2：interfaces代理需要实现的所有接口
		 * 		方式1：userService.getClass().getInterfaces()
		 * 		方式2：new Class[]{UserService.class}
		 * 	参数3：InvocationHandler 处理类，一般采用匿名内部类
		 * 		提供invoke方法，代理类的每个方法执行都调用invoke方法，
		 * 			Object proxy：代理对象
		 * 			Method method：代理对象当前执行的方法的描述对象
		 * 			Object[] args：方法实际参数
		*/
		UserService proxyService = (UserService) Proxy.newProxyInstance(
								MyBeanFactory.class.getClassLoader(), 
								userService.getClass().getInterfaces(), 
								new InvocationHandler() {
									
									@Override
									public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
										//前执行
										myAspect.before();
										//目标类方法
										Object obj = method.invoke(userService, args);
										//后执行
										myAspect.after();
										
										return obj;
									}
								});
		return proxyService;
	}
}
