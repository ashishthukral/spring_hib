package com.tbone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="User_Address")
public class UserAddress {

	private Integer _userId;

	private String street;

	private String city;

	private String state;

	private String country;

	private User _user;

	public UserAddress() {

	}
	
	public String toString(){
		return city+","+state+","+country;
	}

	@Id
	@Column(name="user_id", unique=true, nullable=false)
	@GeneratedValue(generator="myGen")
	@GenericGenerator(name="myGen", strategy="foreign", parameters=@Parameter(name="property", value="user"))
	public Integer getUserId() {
		return _userId;
	}

	public void setUserId(Integer iUserId) {
		_userId = iUserId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String iStreet) {
		street = iStreet;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String iCity) {
		city = iCity;
	}

	public String getState() {
		return state;
	}

	public void setState(String iState) {
		state = iState;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String iCountry) {
		country = iCountry;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return _user;
	}

	public void setUser(User iUser) {
		_user = iUser;
	}
	
	

}
