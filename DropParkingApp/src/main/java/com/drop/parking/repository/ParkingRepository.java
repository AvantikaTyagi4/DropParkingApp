package com.drop.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drop.parking.entity.Parking;

/**
 * Repository for Interacting with Parking Entity
 * 
 * @author Avantika Tyagi
 *
 */
@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

	Parking findByParkingSlot(String parkingSlot);
	
	Parking findByLicenceNumber (String licenceNumber);
}
