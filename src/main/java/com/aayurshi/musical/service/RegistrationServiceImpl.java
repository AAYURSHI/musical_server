package com.aayurshi.musical.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aayurshi.musical.dao.RegistrationDAO;
import com.aayurshi.musical.model.User;

@Service(value = "registrationService")
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private RegistrationDAO registrationDAO;

	@Override
	public User authenticateUser(String emailId, String password) throws Exception {

		User user = null;
		String userEmailIdFromDAO = registrationDAO.authenticateUser(emailId.toLowerCase(), password);
		if (userEmailIdFromDAO != null) {

			//user=registrationDAO.authenticateUser(emailId, password);
			user = registrationDAO.getUserByEmailId(userEmailIdFromDAO);
		} else
			throw new Exception("RegistrationService.INVALID_CREDENTIALS");

		return user;

	}

	@Override
	public String registerNewUser(User user) throws Exception {

		String registeredWithEmailId = null;

		// UserValidator.validateUser(user);
		Boolean emailAvailable = registrationDAO.checkAvailabilityOfEmailId(user.getEmailId().toLowerCase());
		if (emailAvailable) {
			String emailIdToDB = user.getEmailId().toLowerCase();

			user.setEmailId(emailIdToDB);
			registeredWithEmailId = registrationDAO.registerNewUser(user);

		} else {
			throw new Exception("RegistrationService.EMAIL_ID_ALREADY_IN_USE");
		}

		return registeredWithEmailId;
	}

}
