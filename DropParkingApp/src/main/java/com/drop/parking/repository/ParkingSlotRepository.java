package com.drop.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drop.parking.entity.ParkingSlot;

/**
 * Repository for Interacting with ParkingSlot Entity
 * 
 * @author Avantika Tyagi
 *
 */
@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

	List<ParkingSlot> findByparkingSlotNameNotIn(List<String> slots);
	
	ParkingSlot findByParkingSlotName(String slotName);

}
