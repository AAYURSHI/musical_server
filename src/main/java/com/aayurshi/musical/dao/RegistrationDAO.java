package com.aayurshi.musical.dao;

import com.aayurshi.musical.model.User;

public interface RegistrationDAO {
	public Boolean checkAvailabilityOfEmailId(String emailId);
	public String registerNewUser (User user);
	public String authenticateUser(String emailId, String password);
//	public String getPasswordOfUser(String emailId) ;
	public User getUserByEmailId(String emailId);
//	public void updateProfile(User user);
//	public void changePassword(String EmailId, String newHashedPassword);
	
}
