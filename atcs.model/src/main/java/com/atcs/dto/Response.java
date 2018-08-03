package com.atcs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

	  private T result;
	
	  private String name;
	  private Integer code;
	  private String message;
	

	  public Response(T result, String name, Integer code, String message) {
	    this.result = result;
	    this.name = name;
	    this.code = code;
	    this.message = message;
	    }

	  public Response() {
	  }

	public T getResult() {
	    return result;
	  }

	  public void setResult(T result) {
	    this.result = result;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public Integer getCode() {
	    return code;
	  }

	  public void setCode(Integer code) {
	    this.code = code;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }

}