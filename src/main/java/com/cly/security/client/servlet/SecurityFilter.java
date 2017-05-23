package com.cly.security.client.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cly.security.ClientSecurityFilterService;
import com.cly.security.ClientSecurityServiceManager;
import com.cly.security.SecurityAuthException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

	private String[] excludeUrls;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		ArrayList<String> al=new ArrayList<String>();
		
		al.add("/rest");
		
		excludeUrls=al.toArray(new String[0]);		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			
			HttpServletRequest req = (HttpServletRequest) request;
			
			String reqUrl=req.getServletPath();
			
			for(String s:this.excludeUrls){
				if(s.equals(reqUrl)){
					chain.doFilter(request, response);
					return;
				}
				
			}

			HttpServletResponse res = (HttpServletResponse) response;

			ClientSecurityFilterService ssf = (ClientSecurityFilterService) ClientSecurityServiceManager
					.getClientSecurityFilterService();

			if (ssf.isInqAuthCodeRequest(request, response))
				return;

			if (!ssf.authenticateUser(request, response)) {

				String loginUrl = ssf.getLoginUrl(request, response);

				res.sendRedirect(loginUrl);

				return;
			} 
			
			String[] ug = { "manager", "admin" };
			if (!ssf.accessPermmission(request, response, ug)) {
				System.out.println("Not  pass permmissions.");
			} else
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
