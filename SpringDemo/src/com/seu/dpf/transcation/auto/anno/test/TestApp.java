package com.seu.dpf.transcation.auto.anno.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seu.dpf.transcation.manual.service.AccountService;

public class TestApp {

	@Test
	public void demo1() {
		ApplicationContext applicationContext = new 
				ClassPathXmlApplicationContext("com/seu/dpf/transcation/auto/anno/applicationContext.xml");
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		accountService.transfer("jack", "rose", 1000);
	}
}
