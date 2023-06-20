package com.drop.parking.service;

import org.springframework.stereotype.Service;

import com.drop.parking.dto.AuthRequest;
import com.drop.parking.dto.ParkDto;
import com.drop.parking.dto.SlotDto;
import com.drop.parking.dto.UnparkDto;
import com.drop.parking.dto.UserDto;

/**
 * Service Interface 
 * 
 * @author Avantika Tyagi
 *
 */
@Service
public interface ParkingService {

	ParkDto parks(String licenceNumber);
	
	SlotDto slot(String slotName);
	
	UnparkDto unpark(String licenceNumber);
	
	UserDto login(AuthRequest authRequest);
}
