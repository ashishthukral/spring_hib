package com.tbone.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audit_log")
public class AuditLog implements Serializable {

	private static final long serialVersionUID = 1L;

	public AuditLog(Long iAuditEntityId, String iAuditEntityDetail, String iAction, String iEntityName) {
		_auditEntityId = iAuditEntityId;
		_auditEntityDetail = iAuditEntityDetail;
		_action = iAction;
		_entityName = iEntityName;
		_createdDate = new Date();
	}

	private Long _id;
	private Long _auditEntityId;
	private String _auditEntityDetail;
	private String _action;
	private Date _createdDate;
	private String _entityName;

	@Id
	@GeneratedValue
	public Long getId() {
		return _id;
	}

	public void setId(Long iId) {
		_id = iId;
	}

	public Long getAuditEntityId() {
		return _auditEntityId;
	}

	public void setAuditEntityId(Long iAuditEntityId) {
		_auditEntityId = iAuditEntityId;
	}

	public String getAuditEntityDetail() {
		return _auditEntityDetail;
	}

	public void setAuditEntityDetail(String iAuditEntityDetail) {
		_auditEntityDetail = iAuditEntityDetail;
	}

	public String getAction() {
		return _action;
	}

	public void setAction(String iAction) {
		_action = iAction;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date iCreatedDate) {
		_createdDate = iCreatedDate;
	}

	public String getEntityName() {
		return _entityName;
	}

	public void setEntityName(String iEntityName) {
		_entityName = iEntityName;
	}

}
