package com.miar.miarcrypt.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Order(2)
public class MiarcryptSecurityConfig extends WebSecurityConfigurerAdapter {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DataSource dataSource;

	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
		logger.debug("Create Authentication Manager");
		auth.jdbcAuthentication().dataSource(dataSource)
		 	.usersByUsernameQuery("select username,password, enabled from users where username=?")
		 	.authoritiesByUsernameQuery("select username, role from user_roles where username=?");

	 } 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.debug("Configure http");
		
		http
		.authorizeRequests()
			.antMatchers("/miar/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().accessDeniedPage("/miar/403")
			.and()
			.formLogin()
			.failureUrl("/app/fail")
			.defaultSuccessUrl("/app/home");

		//			.antMatchers("/miar/**").permitAll()
		
	}
	
	/*
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		
		logger.debug("Create DataSource Bean");
	    BasicDataSource dataSourceLocal = new BasicDataSource();
	    
	    dataSourceLocal.setDriverClassName("org.postgresql.Driver");
	    dataSourceLocal.setUrl("jdbc:postgresql://localhost:5432/MiarCrypt");
	    dataSourceLocal.setUsername("postgres");
	    dataSourceLocal.setPassword("admin123");
	    
	    logger.debug("DataSource Bean OK : " + dataSourceLocal.getInitialSize());
	   
	    return dataSourceLocal;
	}
	*/
	
}
