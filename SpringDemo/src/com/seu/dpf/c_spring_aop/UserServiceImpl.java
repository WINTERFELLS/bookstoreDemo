package com.seu.dpf.c_spring_aop;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("c_spring_aop add user");
	}

	@Override
	public void updateUser() {
		System.out.println("c_spring_aop update user");
	}

	@Override
	public void deleteUser() {
		System.out.println("c_spring_aop delete user");
	}

}
