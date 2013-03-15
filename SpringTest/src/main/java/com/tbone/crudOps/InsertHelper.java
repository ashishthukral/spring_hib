package com.tbone.crudOps;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.tbone.domain.AuditLog;
import com.tbone.domain.Meeting;
import com.tbone.domain.SchoolClass;
import com.tbone.domain.Stock;
import com.tbone.domain.User;
import com.tbone.domain.UserAddress;
import com.tbone.domain.UserCountry;
import com.tbone.domain.UserSchoolClass;
import com.tbone.domain.UserSchoolClassId;
import com.tbone.interceptor.AuditLogInterceptor;
import com.tbone.interceptor.AuditLogInterface;
import com.tbone.util.HibernateUtil;

public class InsertHelper {

	private static final Logger LOG = Logger.getLogger(InsertHelper.class);

	public void insertStock() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			LOG.info("Inserting Stocks !");
			tx = session.beginTransaction();
			Stock stock = new Stock();
			stock.setStockCode("4715");
			stock.setStockName("GENM");
			session.save(stock);
			stock = new Stock();
			stock.setStockCode("4716");
			stock.setStockName("abcd");
			session.save(stock);
			tx.commit();
			LOG.info("Inserting Stocks SUCCESSFUL !!!");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	/**
	 * Call after insertUser()
	 */
	public void insertUserCountry() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			LOG.info("Inserting UserCountry !");
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			User aDBUser = (User) criteria.add(Restrictions.idEq(1)).uniqueResult();
			UserCountry aUserCountry = new UserCountry();
			aUserCountry.setUser(aDBUser);
			aUserCountry.setIsoCode("ind");
			session.save(aUserCountry);
			aUserCountry = new UserCountry();
			aUserCountry.setUser(aDBUser);
			aUserCountry.setIsoCode("usa");
			session.save(aUserCountry);
			criteria = session.createCriteria(User.class);
			aDBUser = (User) session.load(User.class, 2);
			aUserCountry = new UserCountry();
			aUserCountry.setUser(aDBUser);
			aUserCountry.setIsoCode("rus");
			session.save(aUserCountry);
			aUserCountry = new UserCountry();
			aUserCountry.setUser(aDBUser);
			aUserCountry.setIsoCode("chn");
			session.save(aUserCountry);
			tx.commit();
			LOG.info("Inserting UserCountry SUCCESSFUL !!!");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public void insertNewUserCountry() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			LOG.info("Inserting NewUserCountry !");
			tx = session.beginTransaction();
			User aDBUser = new User();
			aDBUser.setUsername("batman");
			aDBUser.setCreatedBy("ashish");
			aDBUser.setCreatedDate(new Date());
			UserCountry aUserCountry = new UserCountry();
			aUserCountry.setUser(aDBUser);
			aUserCountry.setIsoCode("ind");
			aDBUser.getCountries().add(aUserCountry);
			session.save(aDBUser);
			session.save(aUserCountry);
			tx.commit();
			LOG.info("Inserting NewUserCountry SUCCESSFUL !!!");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public void insertUserManager() {
		AuditLogInterceptor interceptor = new AuditLogInterceptor();
		Session session = HibernateUtil.getSessionFactory().withOptions().interceptor(interceptor).openSession();
		interceptor.setSession(session);
		Transaction tx = null;
		try {
			LOG.info("Inserting User n Manager !");
			tx = session.beginTransaction();
			User aDBUser = new User();
			aDBUser.setUsername("spiderman");
			aDBUser.setCreatedBy("ashish");
			aDBUser.setCreatedDate(new Date());
			User manager = new User();
			manager.setUsername("SUPERMAN");
			manager.setCreatedBy("ashish");
			manager.setCreatedDate(new Date());
			// un-comment below if save on manager
			// manager.getSubordinates().add(aDBUser);
			// setting manager below and save on aDBUser will cascade manager insert too
			aDBUser.setManager(manager);
			// session.save(manager);
			// save on aDBUser with manager set is enough to persist both users as cascade=All
			session.save(aDBUser);
			tx.commit();
			LOG.info("Inserting  User n Manager SUCCESSFUL !!!");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public void insertUserSchoolClass() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			User aDBUser = (User) criteria.add(Restrictions.idEq(2)).uniqueResult();
			SchoolClass aSchoolClass = new SchoolClass();
			aSchoolClass.setCourseName("algo");
			// need to save first for use later in userSchoolClass
			session.save(aSchoolClass);

			// set composite primary key
			UserSchoolClassId aUserSchoolClassId = new UserSchoolClassId();
			aUserSchoolClassId.setSchoolClass(aSchoolClass);
			aUserSchoolClassId.setUser(aDBUser);

			UserSchoolClass aUserSchoolClass = new UserSchoolClass();
			aUserSchoolClass.setCodeName("alg");
			aUserSchoolClass.setId(aUserSchoolClassId);
			session.save(aUserSchoolClass);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public void insertUser() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			LOG.info("Inserting User !");
			tx = session.beginTransaction();
			User aDBUser = new User();
			aDBUser.setUsername("tbone");
			aDBUser.setCreatedBy("ashish");
			aDBUser.setCreatedDate(new Date());
			// insert and associate UserAddress with User
			UserAddress aUserAddress = new UserAddress();
			aUserAddress.setCity("asr");
			aUserAddress.setState("punjab");
			aUserAddress.setCountry("india");
			// add user ref to userAddress
			aUserAddress.setUser(aDBUser);
			// add userAddress ref to user too
			aDBUser.setUserAddress(aUserAddress);
			Meeting aUserMeeting = new Meeting();
			aUserMeeting.setMeetingDate(new Date());
			aUserMeeting.setSubject("profit");
			// add user to userMeeting set
			aUserMeeting.getUsers().add(aDBUser);
			// add userMeeting to user's meetings set
			aDBUser.getMeetings().add(aUserMeeting);
			// you can also comment dbUser save and save on below commented
			// aUserMeeting save as that also has mappedBy and cascadeType.All
			// annotation
			session.save(aDBUser);
			// you don't need below insert as mappedBy means inverse=true
			// (relationship owner), and CascadeType.ALL present in getMeetings
			// of User so saving User object persists meetings too
			// session.save(aUserMeeting);

			// 2nd user
			aDBUser = new User("razor", "ashish", new Date());
			session.save(aDBUser);
			tx.commit();
			LOG.info("Inserting User SUCCESSFUL !!!");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public static void logEntityAudit(String iActionMessage, AuditLogInterface iEntity, Session iSession) {
		try {
			AuditLog auditRecord = new AuditLog(iEntity.getAuditEntityId(), iEntity.getAuditEntityDetail(), iActionMessage, iEntity.getClass().toString());
			iSession.save(auditRecord);
		} catch (HibernateException e) {
			LOG.error("Exception Message", e);
		}
	}
}
