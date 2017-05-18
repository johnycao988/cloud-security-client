package com.cly.security.client.rest.service;

import javax.inject.Singleton;
import javax.ws.rs.GET; 
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 

@Singleton
@Path("/page")
public class LoadPage {

	
	@GET
	@Path("/{pagePath}")
	@Produces(MediaType.APPLICATION_JSON)
	public String load(@PathParam("pagePath") String pagePath) {

	 	return pagePath;
		 
	}
	
 

	
}
