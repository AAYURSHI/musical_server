package com.aayurshi.musical.service;

import com.aayurshi.musical.model.User;

public interface RegistrationService {
	public User authenticateUser(String emailId, String password) throws Exception;
	public String registerNewUser(User user) throws Exception ;

}
