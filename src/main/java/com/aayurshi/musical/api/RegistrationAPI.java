package com.aayurshi.musical.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.aayurshi.musical.model.User;
import com.aayurshi.musical.service.RegistrationService;

@CrossOrigin
@RestController
@RequestMapping("RegistrationAPI")
public class RegistrationAPI {

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private Environment environment;

	static Logger logger = LogManager.getLogger(RegistrationAPI.class.getName());

	@PostMapping(value = "/Login")
	public ResponseEntity<User> authenticateUser(@RequestBody User user) throws Exception {
		try {
			logger.info("VALIDATING CREDENTIALS. CUSTOMER EMAIL ID: " + user.getEmailId());

			User userfromDB = registrationService.authenticateUser(user.getEmailId(), user.getPassword());

			logger.info("LOGIN SUCCESS, CUSTOMER EMAIL : " + userfromDB.getEmailId());

			return new ResponseEntity<User>(userfromDB, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}
	@GetMapping("/hello")
	public String get()
	{
		return "hello";
	}

	@PostMapping(value = "/registerCustomer")
	public ResponseEntity<String> registerUser(@RequestBody User user) throws Exception {
		try {
			logger.info("TRYING TO REGISTER. CUSTOMER EMAIL ID: " + user.getEmailId());

			String registeredWithEmailID = registrationService.registerNewUser(user);
			registeredWithEmailID = environment.getProperty("RegistrationAPI.CUSTOMER_REGISTRATION_SUCCESS");
					//+ registeredWithEmailID;
			return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}
	}

}
