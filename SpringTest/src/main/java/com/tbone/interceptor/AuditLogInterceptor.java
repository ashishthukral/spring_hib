package com.tbone.interceptor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;

import com.tbone.crudOps.InsertHelper;

public class AuditLogInterceptor extends EmptyInterceptor {
	private static final Logger LOG = Logger.getLogger(AuditLogInterceptor.class);

	private static final long serialVersionUID = 1L;
	private Set<AuditLogInterface> inserts = new HashSet<AuditLogInterface>();
	private Set<AuditLogInterface> updates = new HashSet<AuditLogInterface>();
	private Set<AuditLogInterface> deletes = new HashSet<AuditLogInterface>();
	private Session _session;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
		LOG.info("onSave");
		if (entity instanceof AuditLogInterface) {
			inserts.add((AuditLogInterface) entity);
		}
		return false;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
		LOG.info("onFlushDirty");
		if (entity instanceof AuditLogInterface) {
			updates.add((AuditLogInterface) entity);
		}
		return false;

	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		LOG.info("onDelete");
		if (entity instanceof AuditLogInterface) {
			deletes.add((AuditLogInterface) entity);
		}
	}

	// called before commit into database
	@Override
	public void preFlush(Iterator iterator) {
		LOG.info("preFlush");
	}

	public void postFlush(Iterator iterator) {
		LOG.info("postFlush");
		try {
			for (Iterator<AuditLogInterface> it = inserts.iterator(); it.hasNext();) {
				AuditLogInterface entity = it.next();
				LOG.info("postFlush - insert");
				LOG.info(entity.getAuditEntityId() + ",,, " + entity.getAuditEntityDetail() + ",,, " + entity.getClass().toString());
				InsertHelper.logEntityAudit("Saved", entity, _session);
			}
			for (Iterator<AuditLogInterface> it = updates.iterator(); it.hasNext();) {
				AuditLogInterface entity = it.next();
				LOG.info("postFlush - update");
				// InsertHelper.logEntityAudit("Updated", entity);
			}

			for (Iterator<AuditLogInterface> it = deletes.iterator(); it.hasNext();) {
				AuditLogInterface entity = it.next();
				LOG.info("postFlush - delete");
				// InsertHelper.logEntityAudit("Deleted", entity);
			}

		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
	}

	public void setSession(Session iSession) {
		_session = iSession;
	}

}
