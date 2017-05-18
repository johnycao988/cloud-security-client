package com.cly.security.client.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 

@Path("/app")
public class Sys {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String app() {

		return info();

	}
	
	@GET
	@Path("/info")
	@Produces(MediaType.TEXT_PLAIN)
	public String info() {

		return "Cloud Security Client Version 1.0 released on May 8, 2017.";

	}
	
	 

}
