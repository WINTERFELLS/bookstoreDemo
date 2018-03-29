package com.seu.dpf.b_factory_bean;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("b_factory_bean add user");
	}

	@Override
	public void updateUser() {
		System.out.println("b_factory_bean update user");
	}

	@Override
	public void deleteUser() {
		System.out.println("b_factory_bean delete user");
	}

}
