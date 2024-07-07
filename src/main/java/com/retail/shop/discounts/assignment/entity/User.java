package com.retail.shop.discounts.assignment.entity;

import org.springframework.stereotype.Component;

/**
 * @author Shireen
 *
 */
public class User {

	private String userName;
	private String mobileNumber;
	private UserType userType;

	public User(String userName, String mobileNumber, UserType userType) {
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Component
	public static class UserBuilder {
		public User build(String userName, String mobileNumber, UserType userType) {
			return new User(userName, mobileNumber, userType);
		}
	}
}
