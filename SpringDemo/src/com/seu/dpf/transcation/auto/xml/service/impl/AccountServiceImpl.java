package com.seu.dpf.transcation.auto.xml.service.impl;

import com.seu.dpf.transcation.manual.dao.AccountDao;
import com.seu.dpf.transcation.manual.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void transfer(String outer, String inner, Integer money) {
		accountDao.out(outer, money);
		accountDao.in(inner, money);
	}

}
