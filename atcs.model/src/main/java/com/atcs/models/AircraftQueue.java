package com.atcs.models;


import javax.persistence.*;

import com.atcs.util.AircraftEnum.AircraftSize;
import com.atcs.util.AircraftEnum.AircraftType;

@Entity(name = "aircraftqueue")
public class AircraftQueue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aircraftid")
	private Long aircraftid;

	@Enumerated (EnumType.STRING)
	@Column(name = "aircrafttype")
	private AircraftType aircraftType;

	@Enumerated (EnumType.STRING)
	@Column(name = "aircraftsize")
	private AircraftSize aircraftSize;

	public Long getAircraftid() {
		return aircraftid;
	}

	public void setAircraftid(Long aircraftid) {
		this.aircraftid = aircraftid;
	}

	public AircraftType getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(AircraftType aircraftType) {
		this.aircraftType = aircraftType;
	}

	public AircraftSize getAircraftSize() {
		return aircraftSize;
	}

	public void setAircraftSize(AircraftSize aircraftSize) {
		this.aircraftSize = aircraftSize;
	}
	
	

}
