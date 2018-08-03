package com.atcs.dto;

import com.atcs.util.AircraftEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AircraftRequestDTO {

	@JsonProperty("id")
	  private Long  aircraftId;
	
	@JsonProperty("type")
	  private String type;
	
	@JsonProperty("size")
	 private String size;

	public Long getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Long aircraftId) {
		this.aircraftId = aircraftId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}


	
	
}
