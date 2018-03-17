package com.edu.seu.dpf.bookstore.user.service;


import com.edu.seu.dpf.bookstore.user.dao.UserDao;
import com.edu.seu.dpf.bookstore.user.domain.User;

/*
 * User业务层
 */
public class UserService {
	private UserDao userDao = new UserDao();
	
	/*
	 * 注册功能
	 */
	public void regist(User form) throws UserException {
		//校验用户名
		User user = userDao.findByUsername(form.getUsername());
		if(user!=null) {
			throw new UserException("用户名已经注册!");
		}
		
		//校验email
		user = userDao.findByUsername(form.getEmail());
		if(user!=null) {
			throw new UserException("Email已经注册!");
		}
		
		//插入用户到数据库中
		userDao.add(form);
	}
	/*
	 * 激活功能
	 */
	public void active(String code) throws UserException {
		User user = userDao.findByCode(code);
		if(user == null) {
			throw new UserException("激活码无效！");
		}else if(user.isState()) {
			throw new UserException("您已激活！");
		}else {
			userDao.updateState(user.getUid(), true);
		}
	}
	
	/*
	 * 登陆功能
	 */
	public User login(User form) throws UserException {
		User user = userDao.findByUsername(form.getUsername());
		if(user == null) {
			throw new UserException("用户名不存在!");
		}else if(user.getPassword().equals(form.getPassword())) {
			throw new UserException("密码错误！");
		}else if(!user.isState()) {
			throw new UserException("您未激活！");
		}else {
			return user;
		}
	}
}