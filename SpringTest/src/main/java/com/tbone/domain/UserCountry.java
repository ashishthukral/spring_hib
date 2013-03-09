package com.tbone.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "user_country")
public class UserCountry implements Serializable{
	
	private static final long serialVersionUID = 8296571588566858637L;
	private Integer _userCountryId;
	private String _isoCode;
	private User _user;
	
	public String toString(){
		return _isoCode;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_country_ID", unique = true, nullable = false)
	public Integer getUserCountryId() {
		return _userCountryId;
	}
	
	private void setUserCountryId(Integer iUserCountryId) {
		_userCountryId = iUserCountryId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false)
	public User getUser() {
		return _user;
	}
	public void setUser(User iUser) {
		_user = iUser;
	}
	
	@Column(nullable = false,length=10)
	public String getIsoCode() {
		return _isoCode;
	}

	public void setIsoCode(String iIsoCode) {
		_isoCode = iIsoCode;
	}

}
