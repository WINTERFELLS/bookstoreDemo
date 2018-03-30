package com.seu.dpf.transcation.manual.service.impl;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.seu.dpf.transcation.manual.dao.AccountDao;
import com.seu.dpf.transcation.manual.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}


	private TransactionTemplate transcationTemplate;
	public void setTranscationTemplate(TransactionTemplate transcationTemplate) {
		this.transcationTemplate = transcationTemplate;
	}

	@Override
	public void transfer(final String outer, final String inner, final Integer money) {
		transcationTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				accountDao.out(outer, money);
				accountDao.in(inner, money);
			}
		});
	}

}
