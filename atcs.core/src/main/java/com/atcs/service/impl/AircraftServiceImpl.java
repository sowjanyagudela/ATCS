package com.atcs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atcs.dto.AircraftRequestDTO;
import com.atcs.dto.Response;
import com.atcs.models.AircraftQueue;
import com.atcs.rest.exceptions.RestServiceException;
import com.atcs.rest.exceptions.code.GenericErrorCode;
import com.atcs.rest.exceptions.code.GenericErrorMessages;
import com.atcs.service.AircraftDataService;
import com.atcs.service.AircraftService;
import com.atcs.util.AircraftEnum.AircraftSize;
import com.atcs.util.AircraftEnum.AircraftType;

@Service
public class AircraftServiceImpl implements AircraftService {

	@Autowired
	AircraftDataService aircraftDataService;

	/**
	 * Add Aircraft using AircraftRequestDTO returns AircraftQueue with persisted Id
	 */
	@Override
	public Response<AircraftQueue> addAircraft(AircraftRequestDTO aircraftRequestDTO) {
		Response<AircraftQueue> response = new Response<>();
		
		//Validating Request Object
		if(aircraftRequestDTO == null || aircraftRequestDTO.getType() == null || aircraftRequestDTO.getSize() == null) {
			response.setResult(null);
			response.setMessage("Invalid Request");
			response.setCode(HttpStatus.BAD_REQUEST.value());
			response.setName("Invalid Request");
			return response;
		}
		
		//validate  Aircraft Type
		try {
			AircraftType aircraftType = AircraftType.valueOf(aircraftRequestDTO.getType());
		} catch (Exception e) {
			RestServiceException exception = new RestServiceException(GenericErrorCode.INVALID_AIRCRAFT_TYPE,
					GenericErrorMessages.INVALID_AIRCRAFT_TYPE_MSG);
			response.setCode(exception.getStatus());
			response.setMessage(exception.getMessage());
			return response;
		}
		
		//validate  Aircraft Size
		try {
			AircraftSize aircraftSize = AircraftSize.valueOf(aircraftRequestDTO.getSize());
		} catch (Exception e) {
			RestServiceException exception = new RestServiceException(GenericErrorCode.INVALID_AIRCRAFT_SIZE,
					GenericErrorMessages.INVALID_AIRCRAFT_SIZE_MSG);
			response.setCode(exception.getStatus());
			response.setMessage(exception.getMessage());
			return response;
		}
		
		// Add Aircraft 
		AircraftQueue aircraftQueue = aircraftDataService.addAircraft(aircraftRequestDTO);
		response.setResult(aircraftQueue);
		response.setMessage("Added Successfully");
		response.setCode(HttpStatus.OK.value());
		response.setName("Success");
		return response;
	}

	/**
	 * Get List of all Aircrafts
	 */
	@Override
	public Response<List<AircraftQueue>> getAircrafts() {

		Response<List<AircraftQueue>> response = new Response<>();
		List<AircraftQueue> airCraftsQueue = new ArrayList<AircraftQueue>();

		airCraftsQueue = aircraftDataService.getAircrafts();
		response.setResult(airCraftsQueue);
		response.setMessage("Success");
		response.setCode(HttpStatus.OK.value());
		response.setName("Success");
		return response;
	}

	/**
	 * Get Aircraft w.r.t to aircraftId returns AircraftQueue
	 */
	@Override
	public Response<AircraftQueue> getAircraft(Long aircraftId) {

		Response<AircraftQueue> response = new Response<>();
		AircraftQueue aircraftQueue = getAirCraftById(aircraftId);
		if (aircraftQueue == null) {
			RestServiceException exception = new RestServiceException(GenericErrorCode.AIRCRAFT_DOESNT_EXISTS,
					GenericErrorMessages.AIRCRAFT_DOESNT_EXISTS_MSG);
			response.setCode(exception.getStatus());
			response.setMessage(exception.getMessage());
			return response;
		}
		
		response.setResult(aircraftQueue);
		response.setMessage("Success");
		response.setCode(HttpStatus.OK.value());
		response.setName("Success");
		return response;
	}

	/**
	 * Dequeue from the table using aircraftId
	 */
	@Override
	public Response dequeue(Long aircraftId) {
		Response response = new Response<>();
		AircraftQueue aircraftQueue = getAirCraftById(aircraftId);
		if (aircraftQueue == null) {
			RestServiceException exception = new RestServiceException(GenericErrorCode.AIRCRAFT_DOESNT_EXISTS,
					GenericErrorMessages.AIRCRAFT_DOESNT_EXISTS_MSG);
			response.setCode(exception.getStatus());
			response.setMessage(exception.getMessage());
			return response;
		}
		
		// get the Higher priority Aircrafts Type than the current aircraft Type
		List<String> listPriority = getPriorityAircrafts(aircraftQueue.getAircraftType());
		if (!listPriority.isEmpty()) {
			// check if higher priority queue exists in the table
			List<AircraftQueue> aircraftQueues = aircraftDataService.getPriorityAircrafts(listPriority);
			// higher priority aircrafts exists in queue
			if (!aircraftQueues.isEmpty()) {
				RestServiceException exception = new RestServiceException(
						GenericErrorCode.HIGHER_PRIORITY_AIRCRAFT_EXISTS,
						GenericErrorMessages.HIGHER_PRIORITY_AIRCRAFT_EXISTS_MSG);
				response.setCode(exception.getStatus());
				response.setMessage(exception.getMessage());
				return response;
			}
		}
		// check with Aircrafts Type and Size
		if (aircraftQueue.getAircraftSize().equals(AircraftSize.SMALL)) {
			AircraftQueue aircraftQueue1 = aircraftDataService.getLargeAircraftByType(aircraftQueue.getAircraftType());
			if (aircraftQueue1 != null) {
				RestServiceException exception = new RestServiceException(
						GenericErrorCode.HIGHER_PRIORITY_AIRCRAFT_EXISTS,
						GenericErrorMessages.HIGHER_PRIORITY_AIRCRAFT_EXISTS_MSG);
				response.setCode(exception.getStatus());
				response.setMessage(exception.getMessage());
				return response;
			}
		}
		// if the higher priority aircrafts doesnt exist, dequeue from the table
		aircraftDataService.delete(aircraftId);
		response.setMessage("Dequeued Successfully");
		response.setCode(HttpStatus.OK.value());
		response.setName("Success");
		return response;
	}

	// Get the list of PriorityAircrafts based on the AircraftType
	// if aircrafttype is CARGO then the list [EMERGENCY, VIP, PASSENGER]
	// if aircrafttype is PASSENGER then the list [EMERGENCY, VIP]
	private List<String> getPriorityAircrafts(AircraftType aircraftTypeIn) {
		List<String> aircraftTypes = new ArrayList<String>();
		for (AircraftType aircraftType : AircraftType.values()) {
			if (aircraftTypeIn.getValue() > aircraftType.getValue()) {
				aircraftTypes.add(aircraftType.name());
			}
		}
		return aircraftTypes;
	}

	private AircraftQueue getAirCraftById(Long aircraftId) {
	AircraftQueue aircraftQueue = aircraftDataService.getAircraft(aircraftId);
		return aircraftQueue;
	}
	
}
