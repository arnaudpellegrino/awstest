package com.miar.miarcrypt.service;

import java.util.List;

import javax.persistence.Column;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.miar.miarcrypt.common.util.HibernateUtil;
import com.miar.miarcrypt.domain.User;

public class LoginSRVC {
	
	private Log logger = LogFactory.getLog(this.getClass());
	

	@Column(name="login", length=20, nullable=false)
	private String login ;
	
	@Column(name="password", length=20, nullable=false)
	private String password ;
	

	
	public LoginSRVC() {
		super();
	}


	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public User authentication(){
		
		User user = null ;
		//Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //Session session = sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
		//start transaction
        session.beginTransaction();
        Query query = session.createQuery("from User where active='1' and login = :login and password= :pass ");
        query.setParameter("login", login);
        query.setParameter("pass", password);
        
        
        //Execute the Query
        List<?> list = query.list();
        
        
        if (list.size() > 0){
        	user = (User)list.get(0);
        	logger.debug("User ID="+user.getId());            	
        }else
        {
        	logger.debug("User NOT FOUND");
        }
        
        //Commit transaction
        session.getTransaction().commit();
        //terminate session factory, otherwise program won't end
        //sessionFactory.close();
		session.close();
		
		return user;
		
	}
	
	
	
}
