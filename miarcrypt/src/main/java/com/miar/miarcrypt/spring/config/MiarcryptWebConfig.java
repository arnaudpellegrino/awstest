package com.miar.miarcrypt.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import com.miar.miarcrypt.business.CryptBean;
import com.miar.miarcrypt.spring.init.MiarcryptWebInitializer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.miar.miarcrypt.spring.init","com.miar.miarcrypt.spring.config","com.miar.miarcrypt.spring.service","com.miar.miarcrypt.spring.mvc.controller"}, basePackageClasses = MiarcryptWebInitializer.class)
public class MiarcryptWebConfig extends WebMvcConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Bean
	public ViewResolver getViewResolver(){
		logger.debug("Create ViewResolver Bean");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public ViewResolver resourceBundleViewResolver() {
	    ResourceBundleViewResolver bean = new ResourceBundleViewResolver();
	    bean.setBasename("views");
	    return bean;
	}
	
}
