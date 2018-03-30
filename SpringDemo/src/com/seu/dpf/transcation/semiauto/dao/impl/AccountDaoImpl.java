package com.seu.dpf.transcation.semiauto.dao.impl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.seu.dpf.transcation.manual.dao.AccountDao;

public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

	@Override
	public void out(String outer, Integer money) {
		this.getJdbcTemplate().update("update account set money = money - ? where username = ?", money, outer);
	}

	@Override
	public void in(String inner, Integer money) {
		this.getJdbcTemplate().update("update account set money = money + ? where username = ?", money, inner);
	}

}
