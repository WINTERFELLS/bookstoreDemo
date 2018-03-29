package com.seu.dpf.d_aspect.b_anno;

import org.springframework.stereotype.Service;

@Service("userServiceId")
public class UserServiceImpl implements UserService {

	@Override
	public void addUser() {
		System.out.println("d_aspect.b_anno add user");
	}

	@Override
	public void updateUser() {
		System.out.println("d_aspect.b_anno update user");
	}

	@Override
	public void deleteUser() {
		System.out.println("d_aspect.b_anno delete user");
	}

}
