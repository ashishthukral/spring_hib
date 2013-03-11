package com.tbone.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(_user).append(_schoolClass).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		final SchoolClass aSchoolClass = ((UserSchoolClassId) obj).getSchoolClass();
		final User aUser = ((UserSchoolClassId) obj).getUser();
		return new EqualsBuilder().append(_user, aUser).append(_schoolClass, aSchoolClass).isEquals();
	}

}
