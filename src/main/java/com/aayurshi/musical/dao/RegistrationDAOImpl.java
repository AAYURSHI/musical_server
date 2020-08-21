package com.aayurshi.musical.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aayurshi.musical.entity.UserEntity;
import com.aayurshi.musical.model.User;

@Repository(value = "registrationDao")
public class RegistrationDAOImpl implements RegistrationDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {

		Boolean flag = false;
		UserEntity userEntity = null;
		userEntity = entityManager.find(UserEntity.class, emailId);

		if (userEntity == null)
			flag = true;

		return flag;
	}


	@Override
	public String registerNewUser(User user) {

		String registeredWithEmailId = null;
		UserEntity userEntity = new UserEntity();
		userEntity.setEmailId(user.getEmailId());
		userEntity.setUsername(user.getUsername());
		userEntity.setPassword(user.getPassword());

		entityManager.persist(userEntity);
		registeredWithEmailId = userEntity.getEmailId();

		return registeredWithEmailId;
	}

	@Override
	public String authenticateUser(String emailId, String password) {
		Query query = entityManager.createQuery(
				"select u from UserEntity u where u.emailId = '" + emailId + "' and u.password = '" + password + "'");
		List<UserEntity> userEntities = query.getResultList();
		if (userEntities.isEmpty())
			return null;

		return userEntities.get(0).getEmailId();
		// return emailId;
	}

//	@Override
//	public String getPasswordOfUser(String emailId) {
//
//		String password = null;
//		emailId = emailId.toLowerCase();
//		UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
//		if (userEntity != null) {
//			password = userEntity.getPassword();
//
//		}
//
//		return password;
//	}

	@Override
	public User getUserByEmailId(String emailId) {

		User user = null;
		emailId = emailId.toLowerCase();

		UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
		if (userEntity != null) {
			user = new User();
			user.setEmailId(userEntity.getEmailId());
			user.setUsername(userEntity.getUsername());

		}

		return user;
	}

}
