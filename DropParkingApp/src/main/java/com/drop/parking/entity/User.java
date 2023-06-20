package com.drop.parking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for saving User details
 * 
 * @author Avantika Tyagi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
	@Id
	private Long id;
	private String userName;
	private String password;
	private String email;
}
