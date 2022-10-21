package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.LoginException;
import com.masai.module.User;

import com.masai.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao uDao;
	
	private static String passwordChanger;
	
	private static User dummyUser;

	@Override
	public User createNewUser(User user) throws LoginException {
		
		Optional<User> userOptional =  uDao.findByMobile(user.getMobile());
		
		if(userOptional.isPresent()) {
			throw new LoginException("Mobile Number is already register with the Account");
		}
		
		User u1 =  uDao.save(user);
		return u1;
	}

	@Override
	public String forgotPassword(String mobile) throws LoginException {
		Optional<User> userOptional =  uDao.findByMobile(mobile);
		
		if(!userOptional.isPresent()) {
			throw new LoginException("Mobile Number is Not register with the Account");
		}
		
		String key = RandomString.getRandomString();
		passwordChanger = key;
		dummyUser = userOptional.get();
		return key;
	}

	@Override
	public String updatePassword(String uniqueId,String password) throws LoginException {
		if(uniqueId.equals(passwordChanger)) {
			dummyUser.setPassword(password);
			uDao.save(dummyUser);
			dummyUser = null;
		}else {
			throw new LoginException("UniqueID is not matched with currentID");
		}
		
		return "updated";
	}


	
	
	
}
