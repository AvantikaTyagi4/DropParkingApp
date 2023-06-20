package com.drop.parking.exceptions;

import com.drop.parking.enums.Error;

/**
 * Custom Exception class used to throw exception in application
 * 
 * @author Avantika Tyagi
 *
 */
public class ParkingException extends RuntimeException {

	protected String status;
	protected String error;

	public ParkingException() {
	}

	public ParkingException(String status, String error) {
		super();
		this.status = status;
		this.error = error;
	}

	public ParkingException(Error error) {
		super();
		this.status = error.getStatus();
		this.error = error.getMessage();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}