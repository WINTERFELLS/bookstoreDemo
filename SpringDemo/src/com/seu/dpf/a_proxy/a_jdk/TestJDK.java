package com.seu.dpf.a_proxy.a_jdk;

import org.junit.jupiter.api.Test;

public class TestJDK {
	
	@Test
	public void demo01() {
		UserService userService = MyBeanFactory.createService();
		userService.addUser();
		userService.updateUser();
		userService.deleteUser();
	}
}
