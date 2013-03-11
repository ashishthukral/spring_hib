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
public class UserCountry implements Serializable {

	private static final long serialVersionUID = 8296571588566858637L;
	private Integer _userCountryId;
	private String _isoCode;
	private User _user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_country_ID", unique = true, nullable = false)
	public Integer getUserCountryId() {
		return _userCountryId;
	}

	public void setUserCountryId(Integer iUserCountryId) {
		_userCountryId = iUserCountryId;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_ID", nullable = false)
	public User getUser() {
		return _user;
	}

	public void setUser(User iUser) {
		_user = iUser;
	}

	@Column(nullable = false, length = 10)
	public String getIsoCode() {
		return _isoCode;
	}

	public void setIsoCode(String iIsoCode) {
		_isoCode = iIsoCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_userCountryId == null) ? 0 : _userCountryId.hashCode());
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
		UserCountry other = (UserCountry) obj;
		if (_userCountryId == null) {
			if (other._userCountryId != null)
				return false;
		} else if (!_userCountryId.equals(other._userCountryId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserCountry [" + (_userCountryId != null ? "_userCountryId=" + _userCountryId + ", " : "") + (_isoCode != null ? "_isoCode=" + _isoCode : "") + "]";
	}

}
