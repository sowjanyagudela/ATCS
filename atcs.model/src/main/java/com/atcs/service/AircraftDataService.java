package com.atcs.service;

import java.util.List;

import com.atcs.dto.AircraftRequestDTO;
import com.atcs.models.AircraftQueue;
import com.atcs.util.AircraftEnum.AircraftType;

public interface AircraftDataService {

	 AircraftQueue addAircraft(AircraftRequestDTO aircraftRequestDTO);
	
	 AircraftQueue getAircraft(Long aircraftId);
	
	 List<AircraftQueue> getAircrafts();
	
	 void deleteAll();
	
	 void delete(Long aircraftId);
	
	 List<AircraftQueue> getPriorityAircrafts(List<String> priorityList);
	 
	 AircraftQueue getLargeAircraftByType(AircraftType aircraftType);
}
