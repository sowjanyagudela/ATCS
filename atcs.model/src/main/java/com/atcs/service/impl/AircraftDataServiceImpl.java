package com.atcs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.atcs.dto.AircraftRequestDTO;
import com.atcs.dto.Response;
import com.atcs.models.AircraftQueue;
import com.atcs.repository.AircraftRepository;
import com.atcs.service.AircraftDataService;
import com.atcs.util.AircraftEnum.AircraftSize;
import com.atcs.util.AircraftEnum.AircraftType;

@Service
public class AircraftDataServiceImpl implements AircraftDataService {
	@Autowired
	AircraftRepository aircraftRepository;

	/**
	 * Add Aircraft using  AircraftRequestDTO
	 * returns AircraftQueue with persisted Id
	 */
	@Override
	public AircraftQueue addAircraft(AircraftRequestDTO aircraftRequestDTO) {
		AircraftQueue aircraftQueue = addUpdateAircraft(aircraftRequestDTO);
		return aircraftQueue;
	}

	/**
	 * Get List of all Aircrafts
	 */
	@Override
	public List<AircraftQueue> getAircrafts() {
		
		List<AircraftQueue> airCraftsQueue = new ArrayList<AircraftQueue>();
		Iterable<AircraftQueue> aircraftqueueList = aircraftRepository.findAll();
		aircraftqueueList.forEach(airCraftsQueue::add);
		return (List<AircraftQueue>) aircraftqueueList;
	}

	/**
	 * Get Aircraft w.r.t to aircraftId
	 * returns AircraftQueue
	 */
	@Override
	public AircraftQueue getAircraft(Long aircraftId) {
		AircraftQueue airCraftsQueue = aircraftRepository.findOne(aircraftId);
		return airCraftsQueue;
	}

	/**
	 * Dequeue from the table using aircraftId
	 */
	@Override
	public void delete(Long aircraftId) {
		aircraftRepository.delete(aircraftId);

	}

	@Override
	public void deleteAll() {
		aircraftRepository.deleteAll();
	}

	// Add aircraft to the queue
	private AircraftQueue addUpdateAircraft(AircraftRequestDTO aircraftRequestDTO) {
		AircraftQueue aircraftQueue = null;
		if (aircraftRequestDTO != null) {
			aircraftQueue = new AircraftQueue();
			aircraftQueue.setAircraftType(AircraftType.valueOf(aircraftRequestDTO.getType()));
			aircraftQueue.setAircraftSize(AircraftSize.valueOf(aircraftRequestDTO.getSize()));
			aircraftRepository.save(aircraftQueue);
		}
		return aircraftQueue;
	}

	@Override
	public List<AircraftQueue> getPriorityAircrafts(List<String> priorityList) {
		List<AircraftQueue> aircraftQueues = aircraftRepository.getPriorityAirCrafts(priorityList);
		return aircraftQueues;
	}

	@Override
	public AircraftQueue getLargeAircraftByType(AircraftType aircraftType) {
		AircraftQueue aircraftQueue = aircraftRepository.findAircraftQueueByAircraftTypeAndAircraftSize(aircraftType, AircraftSize.LARGE);
		return aircraftQueue;
	}

}
