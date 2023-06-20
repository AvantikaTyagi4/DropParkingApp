package com.drop.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for checking slot
 * 
 * @author Avantika Tyagi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDto {

	private String parkingSlot;
	private String licenceNumber;
	private String slotStatus;
}
