package com.seu.dpf.d_aspect.a_xml;

/*
 * 切面类确定通知，需要实现不同的接口
 */
public class MyAspect{
	public void before() {
		System.out.println("before advice");
	}

}
