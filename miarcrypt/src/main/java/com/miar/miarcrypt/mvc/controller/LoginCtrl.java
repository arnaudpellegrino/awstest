package com.miar.miarcrypt.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.miar.miarcrypt.domain.User;
import com.miar.miarcrypt.model.Login;
import com.miar.miarcrypt.service.LoginSRVC;

@Controller
@SessionAttributes("loggedUser")
@RequestMapping("/infra")
public class LoginCtrl {

	private Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value="/login.page", method = RequestMethod.POST)
    public String viewRegistration(@ModelAttribute("loginForm") Login loginForm, ModelMap model, HttpServletRequest request) {
		
		logger.debug("Login"); 
        
        // TODO : validate datas (not null).
		
        String login = loginForm.getLogin();
        String pass = loginForm.getPassword();
        
        logger.debug("Login : " + login); 
        logger.debug("pass : " + pass);
        
        String errorMessage = "Login and Password must be provided";
        
        if (login == null)
        {
        	model.put("errorMessage", errorMessage );
        	return "index";
        }else if ("".equalsIgnoreCase(login))
        {
        	model.put("errorMessage", errorMessage);
        	return "index";
        }
        if (pass == null)
        {
        	model.put("errorMessage", errorMessage);
        	return "index";
        }else if ("".equalsIgnoreCase(pass))
        {
        	model.put("errorMessage", errorMessage);
        	return "index";
        }


        
        LoginSRVC loginSrvc = new LoginSRVC();
        loginSrvc.setLogin(login);
        loginSrvc.setPassword(pass);
		
		User loggedUser = loginSrvc.authentication();
		
		// END to be refactored
        
		if (loggedUser != null)
		{
			//Add user to Session
			(request.getSession()).setAttribute("loggedUser", loggedUser);
			model.put("loggedUser", loggedUser);
			model.put("message", "Welcome ");
	        return "home";
		}else
		{
			model.put("errorMessage", "Please check your login/password. <br /> Be sure the registration has been confirmed for this user (click the link in the email).");
	        return "index";
		}
			
    }
	
	
	
	@RequestMapping(value="/logout.page", method = RequestMethod.GET)
    public String logout(@ModelAttribute("loginForm") Login loginForm, ModelMap model, HttpServletRequest request) {
		
		logger.debug("Logout"); 
        
		try{
			
			//Clean Session
			HttpSession session = request.getSession();
			session.removeAttribute("loggedUser");
			session.invalidate();
			model.put("confirmMessage", "You are now disconnected from MIARCrypt");
			return "index";
	        
		}catch(Exception e)
		{
			logger.error("Logout Exception : " + e.getMessage());
			model.put("errorMessage", "Logout is not possible. Please close your browser to disconnect");
	        return "index";
		}
			
    }
	
	
}
