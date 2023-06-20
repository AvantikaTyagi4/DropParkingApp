package com.drop.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dto for sending user details
 * @author Avantika Tyagi
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private String username;
	private String authenticationToken;
}
