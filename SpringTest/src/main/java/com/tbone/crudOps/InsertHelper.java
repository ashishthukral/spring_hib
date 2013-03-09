package com.tbone.crudOps;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.tbone.domain.Meeting;
import com.tbone.domain.Stock;
import com.tbone.domain.User;
import com.tbone.domain.UserAddress;
import com.tbone.domain.UserCountry;
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
			aDBUser = (User) criteria.add(Restrictions.idEq(2)).uniqueResult();
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

}