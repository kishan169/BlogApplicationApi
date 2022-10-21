package com.masai.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.LoginException;
import com.masai.Exception.UserException;
import com.masai.module.Login;
import com.masai.module.SessionUser;
import com.masai.repository.SessionDao;
import com.masai.repository.UserDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
public class SessionUserServiceImpl implements SessionUserService{

	@Autowired
	private SessionDao sDao;
	
	@Autowired
	private UserDao uDao;
	
	@Override
	public String logintoAccount(Login login) throws UserException, LoginException {
		
		Optional<com.masai.module.User> optionalUser =  uDao.findByMobile(login.getMobile());
		
		if(!optionalUser.isPresent()) {
			throw new UserException("user Not Present in the List");
		}
		
		Optional<SessionUser> sUser =  sDao.findByUserID(optionalUser.get().getUserID());
		
		if(sUser.isPresent()) {
			throw new LoginException("User already Login with these mobile Number");
		}
		
		if(login.getMobile().equals(optionalUser.get().getMobile()) && login.getPassword().equals(optionalUser.get().getPassword())) {
			
			String key = RandomString.getRandomString();
			SessionUser sessionUser = new SessionUser(optionalUser.get().getUserID(), key, LocalDateTime.now());
			
			sDao.save(sessionUser);
			
			return sessionUser.toString();
		}else {
			throw new LoginException("Invalid Username And password");
		}
		
	}

	@Override
	public String logoutFromAccount(String uuid) throws LoginException {
		
		List<SessionUser> allUser =  sDao.findAll();
		
		Optional<SessionUser> currUser =  sDao.findByUuId(uuid);
		
		if(!currUser.isPresent()) {
			throw new LoginException("You are not Login to the Account");
		}
		
		sDao.delete(currUser.get());
		List<SessionUser> allafterDelete =  sDao.findAll();
		
		if(allafterDelete.size()==allUser.size()) {
			throw new LoginException("Problem in Logout");
		}
		return "Successfully Logout";
	}

}
