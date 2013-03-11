package com.tbone.exec;

import org.apache.log4j.Logger;

import com.tbone.crudOps.InsertHelper;
import com.tbone.crudOps.SelectHelper;
import com.tbone.domain.Stock;
import com.tbone.domain.User;

public class App {

	private static final Logger LOG = Logger.getLogger(App.class);

	public static void main(String[] args) {
		LOG.info("hibernate, hsql/mysql, spring test");
		InsertHelper theInsertHelper = new InsertHelper();
		SelectHelper theSelectHelper = new SelectHelper();
		theInsertHelper.insertStock();
		theInsertHelper.insertUser();
		theInsertHelper.insertUserCountry();
		theSelectHelper.listStocks();
		theSelectHelper.readAll();
		theSelectHelper.readAllCriteria(Stock.class);
		theSelectHelper.readAllCriteriaProjection();
		theSelectHelper.readAllCriteria(User.class);
	}

}
