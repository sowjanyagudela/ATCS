package com.atcs.rest.exceptions.mapper;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.atcs.rest.exceptions.RestServiceException;

@Provider
public class RestServiceExceptionMapper implements ExceptionMapper<RestServiceException> {

	public RestServiceExceptionMapper() {
        System.out.println("Mapper created");
    }

	@Override
	public Response toResponse(RestServiceException umse) {
		  System.out.println("Mapper created to Response");
//		Map<String, Object> responseMap = new HashMap<>();
//		responseMap.put("success", false);
//		responseMap.put("failure_code", umse.getErrorCode());
		int status = umse.getStatus();
		return Response.status(status).entity(new ErrorMessage(umse.getMessage())).type("application/json").build();
	}

}
