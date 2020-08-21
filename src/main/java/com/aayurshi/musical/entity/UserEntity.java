package com.aayurshi.musical.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EK_USER")
public class UserEntity {
	@Id
	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "NAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

//	public String getEmailId() {
//		// TODO Auto-generated method stub
//		return emailId;
//	}
