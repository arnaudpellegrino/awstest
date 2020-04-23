package com.miar.miarcrypt.common.util;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.miar.miarcrypt.domain.User;

public class TestHibernate {
	
		private static Log logger = LogFactory.getLog("TestHibernate");
		 
	    public static void main(String[] args) {
	        User user = new User();
	        user.setName("Arnaud");
	        user.setSurname("PLGRN");
	        user.setCreationTime(new Date());
	        user.setEmail("arnaud@pipo.com");
	        user.setPassword("pass123");
	         
	        //Get Session
	        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	        Session session = sessionFactory.getCurrentSession();
	        //start transaction
	        session.beginTransaction();
	        //Save the Model object
	        session.save(user);
	        //Commit transaction
	        session.getTransaction().commit();
	        logger.debug("Employee ID="+user.getId());
	         
	        //terminate session factory, otherwise program won't end
	        sessionFactory.close();
	    }
	 
	
}
