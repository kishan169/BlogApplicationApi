package com.masai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.LoginException;
import com.masai.Exception.UserException;
import com.masai.module.Login;
import com.masai.module.User;
import com.masai.service.SessionUserService;
import com.masai.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private SessionUserService suService;
	
	@PostMapping("/users")
	public ResponseEntity<User> createNewUser(@RequestBody User user) throws LoginException{
		User us = uService.createNewUser(user);
		return new ResponseEntity<User>(us,HttpStatus.OK);
	}
	
	@PostMapping("/users/{mobile}")
	public ResponseEntity<String> forgetPassword(@PathVariable("mobile") String mobile) throws LoginException{
		String uniqueId =  uService.forgotPassword(mobile);
		return new ResponseEntity<String>(uniqueId,HttpStatus.CREATED);
	}
	
	@PostMapping("/users/{pw}/{id}")
	public ResponseEntity<String> updatePassword(@PathVariable("pw") String password,@PathVariable("id") String uniqueID) throws LoginException{
		String uniqueId =  uService.updatePassword(uniqueID, password);
		return new ResponseEntity<String>(uniqueId,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/users/login")
	public ResponseEntity<String> LoginAccount(@RequestBody Login login) throws UserException, LoginException {
		String str =  suService.logintoAccount(login);
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/logout/{id}")
	public ResponseEntity<String> logoutAccount(@PathVariable("id") String uniqueId) throws LoginException{
		String message = suService.logoutFromAccount(uniqueId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
