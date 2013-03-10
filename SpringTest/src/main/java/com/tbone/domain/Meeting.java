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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meetingId == null) ? 0 : meetingId.hashCode());
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
		Meeting other = (Meeting) obj;
		if (meetingId == null) {
			if (other.meetingId != null)
				return false;
		} else if (!meetingId.equals(other.meetingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meeting [" + (meetingId != null ? "meetingId=" + meetingId + ", " : "") + (subject != null ? "subject=" + subject + ", " : "")
				+ (meetingDate != null ? "meetingDate=" + meetingDate : "") + "]";
	}

}