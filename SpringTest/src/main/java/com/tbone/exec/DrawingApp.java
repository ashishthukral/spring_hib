package com.tbone.exec;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tbone.springBeans.Triangle;

public class DrawingApp {

	private static final Logger LOG = Logger.getLogger(DrawingApp.class);

	public static void main(String[] args) {
		LOG.info("Spring Test start");
		ApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/spring-context.xml");
		Triangle aTriangle = (Triangle) context.getBean("triangle");
		aTriangle.draw();
		LOG.info(aTriangle);
		LOG.info("Spring Test end ");
	}
}