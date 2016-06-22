package br.gov.serpro.sri.servicos.rest;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class App extends Application {

	public App(){
		
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0-SNAPSHOT");
		beanConfig.setHost("localhost:8080");
		beanConfig.setBasePath("/sri-servicos/api");
		beanConfig.setResourcePackage("br.gov.serpro.sri.servicos.rest");
		beanConfig.setScan(true);
		
	}
	
	
	
}
