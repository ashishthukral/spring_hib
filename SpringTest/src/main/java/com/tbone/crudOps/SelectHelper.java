package com.tbone.crudOps;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.tbone.domain.Stock;
import com.tbone.util.HibernateUtil;

public class SelectHelper {

	private static final Logger LOG = Logger.getLogger(SelectHelper.class);

	public void listStocks() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			// tx = session.beginTransaction();
			Stock stock = (Stock) session.get(Stock.class, 1);
			LOG.info(stock.getStockName());
			Criteria criteria = session.createCriteria(Stock.class);
			criteria.add(Restrictions.like("stockCode", "%4%"));
			criteria.setMaxResults(15);
			List<Stock> list = (List<Stock>) criteria.list();
			for (Stock s : list) {
				LOG.info(s.getStockCode());
			}
			criteria = session.createCriteria(Stock.class);
			criteria.add(Restrictions.eq("stockId", 1));
			list = (List<Stock>) criteria.list();
			for (Stock s : list) {
				LOG.info(s.getStockCode());
			}
			// tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public void readAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List<Stock> stocks = (List<Stock>) session.createQuery("FROM Stock").list();
			for (Iterator<Stock> iterator = stocks.iterator(); iterator.hasNext();) {
				Stock aStock = iterator.next();
				LOG.info(aStock.toString());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public void readAllCriteria(Class<?> iClass) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(iClass);
			List<?> list = criteria.list();
			tx.commit();
			// use toString here explicitly, else works like syso
			LOG.info(list.toString());
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

	public void readAllCriteriaProjection() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Stock.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("stockId")).add(Projections.property("stockName")));
			List<Object[]> stocks = (List<Object[]>) criteria.list();
			tx.commit();
			for (Iterator<Object[]> itr = stocks.iterator(); itr.hasNext();) {
				Object[] o = itr.next();
				LOG.info(Arrays.toString(o));
			}
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			LOG.error("Exception Message", e);
		} finally {
			session.close();
		}
	}

}
