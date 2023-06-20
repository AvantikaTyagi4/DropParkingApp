package com.drop.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Park the vehicle
 * 
 * @author Avantika Tyagi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkDto {

	private String parkingSlot;
	private String licenceNumber;
}
