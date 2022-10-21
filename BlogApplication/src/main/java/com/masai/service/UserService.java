package com.masai.service;

import com.masai.Exception.LoginException;
import com.masai.module.User;

public interface UserService {
	
	public User createNewUser(User user) throws LoginException;
	
	public String forgotPassword(String mobile) throws LoginException;
	
	public String updatePassword(String uniqueId , String password) throws LoginException;
	
}
