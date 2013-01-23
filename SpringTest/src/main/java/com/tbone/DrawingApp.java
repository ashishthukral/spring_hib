package com.tbone;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawingApp {

	private static final Logger LOG = Logger.getLogger(DrawingApp.class);


	public static void main(String[] args) {
		LOG.info("Spring Test start");
		ApplicationContext context=new ClassPathXmlApplicationContext("/META-INF/spring/integration/spring-context.xml");
		Triangle myBean=(Triangle)context.getBean("triangle");
		myBean.draw();
		System.out.println(myBean.getType());
		LOG.info("Spring Test end");
	}

}
