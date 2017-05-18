package com.cly.security.client.servlet;

import java.io.IOException; 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter; 
import javax.servlet.http.HttpServletResponse; 
import com.cly.security.ClientSecurityFilterService;
import com.cly.security.ClientSecurityServiceManager;
import com.cly.security.SecurityAuthException; 

@WebFilter("/*")
public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			
			
			HttpServletResponse res=(HttpServletResponse) response;
			
			ClientSecurityFilterService ssf=(ClientSecurityFilterService) ClientSecurityServiceManager.getClientSecurityFilterService();
			
			if (!ssf.authenticateUser(request, response)) {
				
				String loginUrl=ssf.getLoginUrl(request, response);
				
				res.sendRedirect(loginUrl);				
				
				return ;
			}
			
			String[] ug={"manager","admin"};
			if(!ssf.accessPermmission(request, response, ug)){
				System.out.println("Has not permmissions.");
			}else 			 
				System.out.println("Pass permmissions.");
			
			chain.doFilter(request, response);
			
		} catch (SecurityAuthException e) {
			throw new ServletException(e);
		}

	}

	@Override
	public void destroy() {
	}

}
