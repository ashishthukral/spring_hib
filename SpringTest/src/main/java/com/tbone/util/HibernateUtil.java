package com.tbone.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.tbone.domain.Meeting;
import com.tbone.domain.User;
import com.tbone.domain.UserAddress;
import com.tbone.domain.UserCountry;


public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
		try
		{
			Configuration cfg = new Configuration().addResource("Stock.hbm.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(UserCountry.class).addAnnotatedClass(UserAddress.class).addAnnotatedClass(Meeting.class).configure();
			serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
			return cfg.buildSessionFactory(serviceRegistry);
		}
		catch (Throwable ex){
			System.err.println("Failed to create sessionFactory object."+ ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
