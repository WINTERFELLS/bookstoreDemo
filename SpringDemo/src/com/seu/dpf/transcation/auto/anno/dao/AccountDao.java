package com.seu.dpf.transcation.auto.anno.dao;

public interface AccountDao {

	public void out(String outer, Integer money);
	public void in(String inner, Integer money);
}