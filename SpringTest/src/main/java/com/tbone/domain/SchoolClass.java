package com.tbone.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "school_class")
public class SchoolClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long _id;
	private String _courseName;
	private Set<UserSchoolClass> _userSchoolClasses = new HashSet<UserSchoolClass>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return _id;
	}

	public void setId(Long iId) {
		_id = iId;
	}

	@Column(name = "course_name", length = 50, unique = true)
	public String getCourseName() {
		return _courseName;
	}

	public void setCourseName(String iCourseName) {
		_courseName = iCourseName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.schoolClass")
	public Set<UserSchoolClass> getUserSchoolClasses() {
		return _userSchoolClasses;
	}

	public void setUserSchoolClasses(Set<UserSchoolClass> iUserSchoolClasses) {
		_userSchoolClasses = iUserSchoolClasses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
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
		SchoolClass other = (SchoolClass) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SchoolClass [" + (_id != null ? "_id=" + _id + ", " : "") + (_courseName != null ? "_courseName=" + _courseName : "") + "]";
	}

}
