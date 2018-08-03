package com.atcs.service;

import com.atcs.dto.AircraftRequestDTO;
import com.atcs.dto.Response;
import com.atcs.models.AircraftQueue;

public interface AircraftService {

	Response<AircraftQueue> addAircraft(AircraftRequestDTO aircraftRequestDTO);

	Response getAircraft(Long aircraftId);

	Response getAircrafts();

	Response dequeue(Long aircraftId);
}
