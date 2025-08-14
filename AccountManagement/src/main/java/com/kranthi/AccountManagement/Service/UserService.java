package com.kranthi.AccountManagement.Service;

import java.util.List;

import com.kranthi.AccountManagement.Model.Users;

public interface UserService {
	
	Users createUsers(Users user);
	List<Users> getAllUsers();
	Users getUserById(Long id);
	Users updateUser(Long id, Users user);
	void deleteUser(Long id);

}
