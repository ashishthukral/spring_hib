package com.tbone.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.AccessType;

@Entity
@Table(name = "user_school_class")
@AccessType(value = "property")
@AssociationOverrides({ @AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_ID")),
		@AssociationOverride(name = "id.schoolClass", joinColumns = @JoinColumn(name = "school_class_ID")) })
public class UserSchoolClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6183779361048781138L;
	private UserSchoolClassId _id = new UserSchoolClassId();
	private String _codeName;

	@EmbeddedId
	public UserSchoolClassId getId() {
		return _id;
	}

	public void setId(UserSchoolClassId iId) {
		_id = iId;
	}

	public String getCodeName() {
		return _codeName;
	}

	public void setCodeName(String iCodeName) {
		_codeName = iCodeName;
	}

	@Transient
	public User getUser() {
		return _id.getUser();
	}

	public void setUser(User iUser) {
		_id.setUser(iUser);
	}

	@Transient
	public SchoolClass getSchoolClass() {
		return _id.getSchoolClass();
	}

	public void setSchoolClass(SchoolClass iSchoolClass) {
		_id.setSchoolClass(iSchoolClass);
	}

}
