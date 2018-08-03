package com.atcs.rest.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RestConstraintViolationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
      
    	StringBuilder sb = new StringBuilder();
    	for (ConstraintViolation<?> violation : ((ConstraintViolationException)exception).getConstraintViolations())
    	{
    		sb.append(violation.getMessage()+" ");

    	}
    
    	Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("success", false);
		responseMap.put("failure_code", sb.toString());
		return Response.status(200).entity(responseMap).type(MediaType.APPLICATION_JSON).build();
    }

}