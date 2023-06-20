package com.drop.parking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class for displaying response
 * 
 * @author Avantika Tyagi
 *
 */
@JsonInclude(Include.NON_NULL)
@Setter
@Getter
public class BaseResponse {

	private Boolean success;
	private ErrorDto error;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public ErrorDto getError() {
		return error;
	}

	public void setError(ErrorDto error) {
		this.error = error;
	}

}