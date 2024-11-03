package com.jk.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MyConfig implements WebMvcConfigurer {

	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/api/**")  // Apply to all API paths
	                .allowedOrigins("http://localhost:4200")  // Allow your Angular app's origin
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow these methods
	                .allowedHeaders("*")  // Allow any headers
	                .allowCredentials(true)  // Allow credentials if needed
	                .maxAge(3600);  // Cache the response for 1 hour
	    }
}
