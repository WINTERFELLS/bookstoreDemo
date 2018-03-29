package com.seu.dpf.a_proxy.b_cglib;

import org.junit.jupiter.api.Test;

public class TestCglib {
	
	@Test
	public void demo01() {
		UserServiceImpl userService = MyBeanFactory.createService();
		userService.addUser();
		userService.updateUser();
		userService.deleteUser();
	}
}
