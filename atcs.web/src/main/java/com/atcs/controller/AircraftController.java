package com.atcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcs.dto.AircraftRequestDTO;
import com.atcs.dto.Response;
import com.atcs.models.AircraftQueue;
import com.atcs.service.AircraftService;
import com.atcs.util.UrlPaths;

@RestController
@RequestMapping(value = UrlPaths.AIRCRAFT)
public class AircraftController{

	@Autowired
	AircraftService aircraftService;

	/**
	 * API to add an AirCraft 
	 * @param aircraftRequestDTO
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Response<?> addAircraft(@RequestBody AircraftRequestDTO aircraftRequestDTO) {
		Response<AircraftQueue> response = aircraftService.addAircraft(aircraftRequestDTO);
		return response;
	}

	/**
	 * Returns List of Aircrafts
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Response<?> getAircrafts() {
		Response<List<AircraftQueue>> response = aircraftService.getAircrafts();
		return response;
	}

	/**
	 * Returns AirCrafts w.r.t AircraftId
	 * @param aircraftId
	 * @return
	 */
	@RequestMapping(value = "/{aircraftId}", method = RequestMethod.GET)
	public Response<?> getAircraft(@PathVariable("aircraftId") Long aircraftId) {
		Response<List<AircraftQueue>> response = aircraftService.getAircraft(aircraftId);
		return response;
	}

	/**
	 * Delete Aircraft w.r.t AircraftId
	 * @param aircraftId
	 * @return
	 */
	@RequestMapping(value = "/{aircraftId}", method = RequestMethod.DELETE)
	public Response<?> deleteById(@PathVariable("aircraftId") Long aircraftId) {
		Response response = aircraftService.dequeue(aircraftId);
		return response;
	}

}
