package com.tbone.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 2570971705194842387L;

	private Integer userId;
	private String username;
	private String createdBy;
	private Date createdDate;
	private User _manager;
	private Set<User> _subordinates = new HashSet<User>(0);
	private Set<UserCountry> _countries = new HashSet<UserCountry>(0);
	private UserAddress _userAddress;
	private Set<Meeting> _meetings = new HashSet<Meeting>(0);
	private Set<UserSchoolClass> _userSchoolClasses = new HashSet<UserSchoolClass>(0);

	public User() {
	}

	public User(String username, String createdBy, Date createdDate) {
		this.username = username;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	@Id
	// by default takes auto
	@GeneratedValue
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 5, scale = 0)
	// NOTE: the return type has to be same as field type, autoboxing/'int'
	// wouldn't work even, else getter exception thrown
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/*
	 * Manager and Subordinates example of Self-Join
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "manager_id")
	public User getManager() {
		return _manager;
	}

	public void setManager(User iManager) {
		_manager = iManager;
	}

	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
	public Set<User> getSubordinates() {
		return _subordinates;
	}

	public void setSubordinates(Set<User> iSubordinates) {
		_subordinates = iSubordinates;
	}

	@Column(name = "USERNAME", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "CREATED_BY", nullable = false, length = 20)
	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE", nullable = false, length = 7)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/*
	 * 1) If EAGER used then objects for UserCountry not fetched later but fetched in the same query for fetching DBUser itself using left outer join 2) give getter method name in
	 * mappedBy which is used in UserCountry to map this DBUser class instance which is being referred by _user field or getUser() method currently in UserCountry
	 */
	/*
	 * if CascadeType.ALL not used, userCountry not saved on save of user, only saves User
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	public Set<UserCountry> getCountries() {
		return _countries;
	}

	public void setCountries(Set<UserCountry> iCountries) {
		_countries = iCountries;
	}

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public UserAddress getUserAddress() {
		return _userAddress;
	}

	public void setUserAddress(UserAddress iUserAddress) {
		_userAddress = iUserAddress;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_MEETING", joinColumns = { @JoinColumn(name = "user_ID") }, inverseJoinColumns = { @JoinColumn(name = "MEETING_ID") })
	public Set<Meeting> getMeetings() {
		return _meetings;
	}

	public void setMeetings(Set<Meeting> iMeetings) {
		_meetings = iMeetings;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.user")
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
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
