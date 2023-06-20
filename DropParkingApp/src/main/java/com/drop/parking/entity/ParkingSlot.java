package com.drop.parking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for saving parking slot
 * 
 * @author Avantika Tyagi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingSlot {

	@Id
	@GeneratedValue
	@Column(name = "parking_slot_id")
	private Long parkingSlotId;

	@Column(name = "parking_slot_name")
	private String parkingSlotName;
}
