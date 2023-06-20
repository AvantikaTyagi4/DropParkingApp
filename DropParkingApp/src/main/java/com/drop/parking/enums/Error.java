package com.drop.parking.enums;

/**
 * Generic Error enum for sending error message
 * 
 * @author Avantika Tyagi
 *
 */
public enum Error {

	INACTIVE_USER("403", "Inactive User!"), PARKING_SLOT_NOT_EMPTY("404", "All Parking Slots full");

	private String status;
	private String message;

	private Error(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return this.status;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
