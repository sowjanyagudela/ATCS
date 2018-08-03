package com.atcs.rest.exceptions.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.atcs.rest.exceptions.RestConstraintViolationExceptionMapper;
import com.atcs.rest.exceptions.RestServiceException;
import com.atcs.rest.exceptions.mapper.RestServiceExceptionMapper;

public class ExepJerseyConfiguration extends ResourceConfig {

	
	public ExepJerseyConfiguration() {
		register(RestServiceExceptionMapper.class);
		
}
}