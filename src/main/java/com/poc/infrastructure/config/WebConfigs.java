package com.poc.infrastructure.config;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfigs {
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();		
		return multipartResolver;
	}

}
