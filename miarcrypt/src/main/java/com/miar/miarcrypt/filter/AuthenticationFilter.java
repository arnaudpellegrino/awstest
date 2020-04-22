package com.miar.miarcrypt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.miar.miarcrypt.domain.User;

public class AuthenticationFilter implements Filter {

	private Log logger = LogFactory.getLog(this.getClass());
	
	String excludePattern = null;
	boolean doFilter = true;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		logger.debug("doFILTER");
		HttpServletRequest request = (HttpServletRequest) req;
		
		String url = request.getRequestURL().toString();
		logger.debug("URL : " + url);
		
		
		if (matchExcludePattern(url)) {
			logger.debug("doFILTER : exclude");
		    chain.doFilter(request, resp);
		    return;
		    
		}else
		{
			logger.debug("doFILTER : not exclude");
			HttpServletResponse response = (HttpServletResponse) resp;
				//HttpSession session = request.getSession(false);
				
				HttpSession session = request.getSession(false) == null ? request.getSession(true) : request.getSession(false) ;  
				
				User loggedUser = (User)session.getAttribute("loggedUser");
			
				if (loggedUser == null)
				{
					logger.debug("User NOT Logged");
					response.sendRedirect("/MIARCrypt/infra/index.page");
					
				}else
				{
					logger.debug("Logged user : " + loggedUser.getName());
					chain.doFilter(request, resp);
				    return;
				}
				
			
		}
	
		
	}

	
	private boolean matchExcludePattern(String url)
	{
		boolean doFilter = url.contains(excludePattern) ? true : false;
		logger.debug("doFILTER : " + doFilter);
		return doFilter;
	}
	
	
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		
		excludePattern = cfg.getInitParameter("excludePattern");
		logger.debug("ExcludePagePattern :" + excludePattern);
	
	}

}
