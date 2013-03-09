package com.tbone.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Meeting {

	private Long meetingId;
	private String subject;
	private Date meetingDate;
	private Set<User> _users = new HashSet<User>(0);

	public String toString() {
		return subject + ", " + meetingDate;
	}

	@Id
	@Column(name = "MEETING_ID")
	@GeneratedValue
	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long iMeetingId) {
		meetingId = iMeetingId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String iSubject) {
		subject = iSubject;
	}

	@Column(name = "MEETING_DATE")
	@Temporal(TemporalType.DATE)
	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date iMeetingDate) {
		meetingDate = iMeetingDate;
	}

	@ManyToMany(mappedBy = "meetings", cascade = CascadeType.ALL)
	public Set<User> getUsers() {
		return _users;
	}

	public void setUsers(Set<User> iUsers) {
		_users = iUsers;
	}

}