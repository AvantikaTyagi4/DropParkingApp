package com.drop.parking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for storing parking data
 * @author Avantika Tyagi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parking")
public class Parking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parking_id")
	private Long parkingId;

	@Column(name = "parking_slot")
	private String parkingSlot;

	@Column(name = "licence_number")
	private String licenceNumber;

}
