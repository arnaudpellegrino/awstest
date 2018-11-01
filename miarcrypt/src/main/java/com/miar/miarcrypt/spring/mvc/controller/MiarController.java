package com.miar.miarcrypt.spring.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/miar")
public class MiarController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		logger.debug("/hello method");
		return "hello";
	 }
	
	
	@RequestMapping(value = "/privet", method = RequestMethod.GET)
	public String privet(ModelMap model) {
		logger.debug("/privet method");
		return "privet";
	 }
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String noAuthent(ModelMap model) {
		logger.debug("/403 method");
		return "403";
	 }

}
