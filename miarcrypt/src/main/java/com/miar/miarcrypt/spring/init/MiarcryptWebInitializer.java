package com.miar.miarcrypt.spring.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.miar.miarcrypt.service.DemoMessageService;
import com.miar.miarcrypt.service.MessageService;
import com.miar.miarcrypt.spring.config.MiarcryptWebConfig;

public class MiarcryptWebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void onStartup(ServletContext servletContext) throws ServletException {
	  	logger.debug("INIT SPRING WEB");
	  	WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringMvcDispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        //dispatcher.addMapping("/app**");
    }
	
	private AnnotationConfigWebApplicationContext getContext() {
		logger.debug("GET MVC CONFIG");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.miar.miarcrypt.spring.config");
        logger.debug("END MVC CONFIG");
        return context;
    }

	
//	@Bean(name = "dataSource")
//	public DataSource getDataSource() {
//		
//		logger.debug("Create DataSource Bean");
//	    BasicDataSource dataSourceLocal = new BasicDataSource();
//	    
//	    dataSourceLocal.setDriverClassName("org.postgresql.Driver");
//	    dataSourceLocal.setUrl("jdbc:postgresql://localhost:5432/MiarCrypt");
//	    dataSourceLocal.setUsername("postgres");
//	    dataSourceLocal.setPassword("admin123");
//	    
//	    logger.debug("DataSource Bean OK : " + dataSourceLocal.getInitialSize());
//	   
//	    return dataSourceLocal;
//	}

	
	
	
	@Bean(name = "messageService")
	public MessageService getMessageService() {
		
		logger.debug("Create MessageService Bean");
		MessageService messageService = new DemoMessageService();
	     logger.debug("messageService Bean OK");
	   
	    return messageService;
	}

	

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.debug("RETURN SPRING CONFIG CLASSES");
		return new Class[] {MiarcryptWebConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return null;
	}



	
}
