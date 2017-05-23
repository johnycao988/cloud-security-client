package com.cly.security.app;

 

import java.util.Properties;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import com.cly.comm.client.config.ConfigClient;
import com.cly.security.ClientSecurityServiceManager;

 

@ApplicationPath("/rest")
public class ClientApp extends ResourceConfig {

	
	
	public ClientApp() {
		init();
	}

	private void init() {

		packages("com.cly.security.client.rest.service");
		
		ClientApp.initSecurityClientFilter();
		
		System.out.println("init rest ok.");

	}
	
	public static void initSecurityClientFilter(){

		try {

			Properties prop = ConfigClient.getProperties("/cloud.security/cloud.security.client.properties");

			ClientSecurityServiceManager.init(prop); 

		} catch (Exception e) {
			
			Logger.getGlobal().warning(e.getMessage());

		}
	}

	

}