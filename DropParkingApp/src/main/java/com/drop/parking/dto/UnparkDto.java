package com.drop.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for unparking vehicle
 * 
 * @author Avantika Tyagi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnparkDto {

	private String parkingSlot;
	private String licenceNumber;
}
