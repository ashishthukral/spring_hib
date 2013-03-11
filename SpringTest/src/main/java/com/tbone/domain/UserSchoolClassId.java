package com.tbone.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserSchoolClassId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5299294493711421961L;
	private User _user;
	private SchoolClass _schoolClass;

	@ManyToOne
	public User getUser() {
		return _user;
	}

	public void setUser(User iUser) {
		_user = iUser;
	}

	@ManyToOne
	public SchoolClass getSchoolClass() {
		return _schoolClass;
	}

	public void setSchoolClass(SchoolClass iSchoolClass) {
		_schoolClass = iSchoolClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_schoolClass == null) ? 0 : _schoolClass.hashCode());
		result = prime * result + ((_user == null) ? 0 : _user.hashCode());
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
		UserSchoolClassId other = (UserSchoolClassId) obj;
		if (_schoolClass == null) {
			if (other._schoolClass != null)
				return false;
		} else if (!_schoolClass.equals(other._schoolClass))
			return false;
		if (_user == null) {
			if (other._user != null)
				return false;
		} else if (!_user.equals(other._user))
			return false;
		return true;
	}

}
