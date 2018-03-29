package com.seu.dpf.a_proxy.a_jdk;

public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("a_proxy.a_jdk add user");
	}

	@Override
	public void updateUser() {
		System.out.println("a_proxy.a_jdk update user");
	}

	@Override
	public void deleteUser() {
		System.out.println("a_proxy.a_jdk delete user");
	}

}
