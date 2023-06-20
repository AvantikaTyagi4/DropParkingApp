package com.drop.parking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Custom response object needed for the JSON output
 * 
 * @author Avantika Tyagi
 *
 */
@JsonPropertyOrder({ "success", "statusCode", "total", "data" })
public class ResponseDto<R> extends BaseResponse {
	@JsonInclude(Include.NON_NULL)
	private R data;

	@JsonInclude(Include.NON_NULL)
	private Integer total;

	@JsonInclude(Include.NON_NULL)
	private String statusCode = "200";

	/**
	 * @return the data
	 */
	public R getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(R data) {
		this.data = data;
	}

	/**
	 * Returns the HTTP Response code.
	 * 
	 * @return String
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the HTTP Response code.
	 * 
	 * @param statusCode String
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Returns the total # of records when paginating.
	 * 
	 * @return Integer
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * Returns the total records in the recordset.
	 * 
	 * @param total int
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	@SuppressWarnings("unchecked")
	public void addSingleObject(Object object) {
		if (object != null)
			data = ((R) object);

		if (data != null)
			this.total = 1;
		else
			this.total = 0;
	}
}
