package com.miar.miarcrypt.spring.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/app")
public class AppController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/hola", method = RequestMethod.GET)
	public String hola(ModelMap model) {
		logger.debug("/hola method");
		return "hola";
	 }
	
	
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public String message(ModelMap model) {
		logger.debug("/message method");
		return "message";
	 }
	
	@RequestMapping(value = "/home")
	public String home(ModelMap model) {
		logger.debug("/Home method");
		return "home";
	 }
	
	@RequestMapping(value = "/fail")
	public String fail(ModelMap model) {
		logger.debug("/Fail method");
		return "fail";
	 }

	
	//, method = RequestMethod.GET
}
