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
@Table(name = "User_Address")
public class UserAddress {

	private Integer _userId;

	private String street;

	private String city;

	private String state;

	private String country;

	private User _user;

	public UserAddress() {

	}

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	@GeneratedValue(generator = "myGen")
	@GenericGenerator(name = "myGen", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
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

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public User getUser() {
		return _user;
	}

	public void setUser(User iUser) {
		_user = iUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_userId == null) ? 0 : _userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAddress other = (UserAddress) obj;
		if (_userId == null) {
			if (other._userId != null)
				return false;
		} else if (!_userId.equals(other._userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserAddress [" + (_userId != null ? "_userId=" + _userId + ", " : "") + (street != null ? "street=" + street + ", " : "")
				+ (city != null ? "city=" + city + ", " : "") + (state != null ? "state=" + state + ", " : "") + (country != null ? "country=" + country : "") + "]";
	}

}
