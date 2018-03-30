package com.seu.dpf.transcation.auto.anno.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.seu.dpf.transcation.manual.dao.AccountDao;
import com.seu.dpf.transcation.manual.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	@Override
	@Transactional
	public void transfer(String outer, String inner, Integer money) {
		accountDao.out(outer, money);
		accountDao.in(inner, money);
	}

}
