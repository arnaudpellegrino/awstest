package com.miar.miarcrypt.mvc.controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//TODO : Check how to manage Authentication
//import com.miar.miarcrypt.common.util.HibernateUtil;
import com.miar.miarcrypt.domain.User;
import com.miar.miarcrypt.model.Inscription;
import com.miar.miarcrypt.model.Login;

@Controller
@RequestMapping("/infra")
public class InscriptionCtrl {
	
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	//@RequestMapping(value="/infra/index.page", method = RequestMethod.GET)
	@RequestMapping(value="/index.page", method = RequestMethod.GET)
	public String viewRegistration(ModelMap model) {
		//Inscription inscriptionForm = new Inscription();    
		Login loginForm = new Login();
        model.put("loginForm", loginForm);
       
 	    return "index";
    }
	
	
	@RequestMapping(value="/saveInscription.page", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("inscriptionForm") Inscription inscription,
    		ModelMap model) {
         
        // implement your own registration logic here...
		logger.debug("Save Inscription"); 
        
        // TODO : validate datas (confirm pass, etc).
        User user = new User();
        user.setName(inscription.getName());
        user.setSurname(inscription.getSurname());
        user.setEmail(inscription.getEmail());
        user.setLogin(inscription.getLogin());
        user.setPassword(inscription.getPassword());
        user.setCreationTime(new Date());
        //user.setUserConfirmed('0');
        user.setActive('0');
        // Get unique ID for confirmation
        Date now = new Date();
        long idConfirm = now.getTime();
        user.setIdConfirm(idConfirm);
        
        
      //TODO : Check how to manage Registration
        
        
        // TODO : refactor with a Service (xxxxxSRVC class)
        // START To be refactored
		        //Get Session
        
        /*
        		try{
        			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    		        org.hibernate.Session session = sessionFactory.openSession();
    		        //start transaction
    		        Transaction transaction = session.beginTransaction();
    		        //Save the Model object
    		        session.save(user);
    		        //Commit transaction
    		        session.getTransaction().commit();
    		        logger.debug("Registered User ID="+user.getId());
    		        inscription.setId(user.getId());
    		         
    		        //terminate session factory, otherwise program won't end
    		        //sessionFactory.close();
    		        //transaction.commit();
    		        session.close();
    		        	
        			
        		}catch(ConstraintViolationException cve)
        		{
        			logger.error("ERREUR CVE : " + cve.getMessage());
        			model.put("errorMessage", "This email and/or username is already used" );
		            return "inscription";
        		}
		        
		        //SEND EMAIL
        		try{
			        //MailSender mailSender = new JavaMailSenderImpl();
			        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			        mailSender.setHost("pop.gmail.com");
			        mailSender.setUsername("miarcrypt");
			        mailSender.setPassword("miar2015");
			        
			        Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", "587");
					
					mailSender.setJavaMailProperties(props);
			        //SimpleMailMessage templateMessage;
			        //SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
					Session mailSession = Session.getDefaultInstance(props);
					MimeMessage msg = new MimeMessage(mailSession);
					
					//msg.setFrom("no-reply@miarcrypt.com");
			        msg.setSubject("MIARCrypt - Registration");
			        //msg.setReplyTo("miarcrypt@gmail.com");
			        
			        //msg.set(user.getEmail());
			        
			        msg.addRecipient(
			                Message.RecipientType.TO,
			                new InternetAddress(user.getEmail()));
			        
			        msg.setText(
			            "Dear " + user.getName()
			                + ",<br/><br/> Thank you for registering to MIARCrypt, it's almost done ! <br/> Your LOGIN is <b>"
			                + user.getLogin()
			                + "</b> <br/> please confirm your registration by clicking <a href='http://localhost:8080/MIARCrypt/infra/inscription/"+ idConfirm +"/confirm.page'>here</a> "
			                + "<br/><br /> The <a href='http://www.liberation.com'>MIARCrypt</a> Team "
			                , "UTF-8", "html");
			        
			            mailSender.send(msg);
		        }
		        catch (MailException ex) {
		            // simply log it and go on...
		        	logger.error(ex.getMessage());
		            model.put("errorMessage", "Email address is not correct" );
		            return "inscription";
		            
		        }catch (MessagingException mex) {
		            // simply log it and go on...
		        	logger.error(mex.getMessage());
		            model.put("errorMessage", "Sending email is not possible" );
		            return "inscription";
		        }
		        
		   */     
        // END to be refactored
		        
		// TODO : Redirect to inscription to wait for he user to validate the account    
        model.put("confirmMessage", "Registration is almost done ! <br /> Please check your emails and confirm your account to be able to login" );
		return "inscription";      
		// return "index";
        
    }
	
	@RequestMapping(value="/inscription.page", method = RequestMethod.GET)
    public String viewRegistration(@ModelAttribute("inscriptionForm") Inscription inscription,
    		ModelMap model) {
         
        // implement your own registration logic here...
		logger.debug("View Inscription"); 
		
		return "inscription";
		
    }
	
	
	@RequestMapping(value="/springMVC/{testVar}/test.page", method = RequestMethod.GET)
    public String testMapping(@PathVariable("testVar") String testVar, ModelMap model) {
         
        // implement your own registration logic here...
		logger.debug("View TEST"); 
		logger.debug("TestVar = " + testVar );
		
		Login loginForm = new Login();
        model.put("loginForm", loginForm);
		return "index";
    }
	
	@RequestMapping(value="/inscription/{confirmId}/confirm.page", method = RequestMethod.GET)
	public String confirmInscription(@PathVariable("confirmId") Long confirmId, ModelMap model){
		
				logger.debug("Confirm Id : " + confirmId);
				// ?{confirmId} 
				Login loginForm = new Login();
		        model.put("loginForm", loginForm);
		
		      //TODO : Check how to manage Inscription
		      /*
		         try{
	        			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	    		        //org.hibernate.Session session = sessionFactory.getCurrentSession();
	    		        org.hibernate.Session session = sessionFactory.openSession();
	    		        
	    		        //start transaction
	    		         Transaction transaction = session.beginTransaction();
	    		        //Query query = session.createQuery("from User where idConfirm = :idConfirm and active='0' ");
	    		        
	    		        //Query query = session.createQuery("update User set active = '1'" +
	    	    		//		" where idConfirm = :idConfirm and active='0'");
	    		        
//	    		        Query query = session.createQuery("update User set active = '1' where idConfirm = :idConfirm ");
//	    		    	logger.debug("Query = " + query.getQueryString());
//	    		        query.setParameter("idConfirm", confirmId);
//	    		        int result = 0 ; // query.executeUpdate();
	    		        
	    		        Query query2 = session.createQuery("from User where idConfirm = :idConfirm ");
	    		    	logger.debug("Query2 = " + query2.getQueryString());
	    		        query2.setParameter("idConfirm", confirmId);
	    		        User userToConfirm = (User)query2.uniqueResult();
	    		        
	    		        logger.debug("userToConfirm : " + userToConfirm.getName());
	    		        
	    		        userToConfirm.setActive('1');
	    		        session.update(userToConfirm);
	    		        
	    		        logger.debug("user confirmed : " + userToConfirm.getActive());
	    		        
	    		        //terminate session factory, otherwise program won't end
	    		        //sessionFactory.close();
	    		        transaction.commit();
	    		        session.close();
	    		        
	    		        model.put("confirmMessage", "The registration is completed ! <br /> You can connect to the application" );
	    		        
//	    		        logger.debug("Result update = " + result);
//	    		        
//	    		        if (result>0)
//	    		        {
//	    		        	model.put("confirmMessage", "The registration is completed ! <br /> You can connect to the application" );
//	    		        	
//	    		        }else
//	    		        {
//	    		        	model.put("errorMessage", "This user does not exist or is already active" );
//		    		    }
	    		        
			            return "index";
	    		       
	        		}catch(Exception ex)
	        		{
	        			logger.error("ERREUR : " + ex.getMessage());
	        			model.put("errorMessage", "This email/Username  does not exist" );
			            return "index";
	        		}
	        	*/
		        return "index";
		        
	}
	
	
}