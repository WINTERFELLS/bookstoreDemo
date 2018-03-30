package com.seu.dpf.transcation.semiauto.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seu.dpf.transcation.semiauto.service.AccountService;


public class TestApp {

	@Test
	public void demo1() {
		ApplicationContext applicationContext = new 
				ClassPathXmlApplicationContext("com/seu/dpf/transcation/semiauto/applicationContext.xml");
		AccountService accountService = (AccountService) applicationContext.getBean("proxyAccountService");
		accountService.transfer("jack", "rose", 1000);
	}
}
