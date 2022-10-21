package com.masai.service;

import com.masai.Exception.LoginException;
import com.masai.Exception.UserException;
import com.masai.module.Login;

public interface SessionUserService {
	
	public String logintoAccount(Login login) throws UserException, LoginException;
	
	public String logoutFromAccount(String uuid) throws LoginException;
}
