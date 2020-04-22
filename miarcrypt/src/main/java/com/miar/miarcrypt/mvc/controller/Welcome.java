package com.miar.miarcrypt.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Welcome {

	/*
	@RequestMapping(value = "/welcome.page", method = RequestMethod.GET)
	public String printHello(ModelMap model) {
	      model.addAttribute("message", "Welcome bro' ");
	      model.addAttribute("message2", "Cette appli va être super !");
	      model.addAttribute("message3", "Tu verras, t'es pas venu pour rien. ");
	      model.addAttribute("command", new User());
	      
	      return "index";
	   } 
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	   public String login(@ModelAttribute("SpringWeb") User user, 
	   ModelMap model) {
	      model.addAttribute("name", user.getName());
	      model.addAttribute("surname", user.getSurname());
	      model.addAttribute("id", user.getId());
	      return "welcome";
	   }
	*/
	
	@RequestMapping(value = "/home.page", method = RequestMethod.GET)
	public String login(ModelMap model) {
		
		return "home";
	 }
	
}
