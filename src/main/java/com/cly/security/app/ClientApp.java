package com.cly.security.app;

 

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

 

@ApplicationPath("/rest")
public class ClientApp extends ResourceConfig {

	
	
	public ClientApp() {
		init();
	}

	private void init() {

		packages("com.cly.security.client.rest.service");
		
		System.out.println("init rest ok.");

	}

	

}