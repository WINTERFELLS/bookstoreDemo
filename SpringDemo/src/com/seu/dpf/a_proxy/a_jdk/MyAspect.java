package com.seu.dpf.a_proxy.a_jdk;

public class MyAspect {
	public void before() {
		System.out.println("start");
	}
	public void after() {
		System.out.println("end");
	}
}
