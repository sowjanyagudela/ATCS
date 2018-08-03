package com.atcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.atcs.models.AircraftQueue;
import com.atcs.util.AircraftEnum.AircraftSize;
import com.atcs.util.AircraftEnum.AircraftType;

@Repository
public interface AircraftRepository extends JpaSpecificationExecutor<AircraftQueue>,
PagingAndSortingRepository<AircraftQueue, Long> {
	
	  AircraftQueue findAircraftQueueByAircraftTypeAndAircraftSize(AircraftType type,AircraftSize aircraftSize);
	 
	  List<AircraftQueue> findAircraftQueueByAircraftType(AircraftType type);

	  @Query(value = "select * from aircraftqueue as acq where acq.aircraftType in (:list)", nativeQuery = true)
	  List<AircraftQueue> getPriorityAirCrafts(@Param("list") List<String> list);
	
}
