package com.drop.parking.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drop.parking.dto.AuthRequest;
import com.drop.parking.dto.ErrorDto;
import com.drop.parking.dto.ParkDto;
import com.drop.parking.dto.ResponseDto;
import com.drop.parking.dto.SlotDto;
import com.drop.parking.dto.UnparkDto;
import com.drop.parking.dto.UserDto;
import com.drop.parking.exceptions.ParkingException;
import com.drop.parking.service.ParkingService;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

/**
 * 
 * @author Avantika Tyagi
 *
 */
@RestController
@RequestMapping("/parking")
public class ParkingController {

	@Autowired
	private ParkingService parkingService;

	private final Bucket bucket;

	public ParkingController() {
		Bandwidth limit = Bandwidth.classic(429, Refill.greedy(429, Duration.ofMinutes(1)));
		this.bucket = Bucket4j.builder().addLimit(limit).build();
	}

	/**
	 * This method for authenticating user
	 * 
	 * @param authRequest
	 * @return
	 */
	@ApiOperation(value = "Authenticate User", response = UserDto.class, notes = "Provide Request Body with username and password")
	@PostMapping(value = "/login")
	ResponseDto<UserDto> login(@RequestBody AuthRequest authRequest) {

		ResponseDto<UserDto> responseDto = new ResponseDto<UserDto>();

		try {
			if (bucket.tryConsume(1)) {
				UserDto userDto = parkingService.login(authRequest);
				responseDto.setSuccess(true);
				responseDto.setData(userDto);
			} else {
				responseDto.setError(new ErrorDto(Integer.toString(HttpStatus.TOO_MANY_REQUESTS.value()),
						HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase()));
			}
		} catch (ParkingException e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto(e.getStatus(), e.getError()));
		} catch (Exception e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto("501", " Error while authenticating user"));
		}
		return responseDto;
	}

	/**
	 * This method is for parking the vehicle
	 * 
	 * @param licenceNumber
	 * @return ResponseDto<ParkDto> responseDto
	 */
	@ApiOperation(value = "Park vehicle in parking",authorizations = { @Authorization(value="JWT") }, response = ParkDto.class, notes = "License number is required")
	@PostMapping(value = "/park/{licenceNumber}")
	ResponseDto<ParkDto> park(@PathVariable String licenceNumber) {

		ResponseDto<ParkDto> responseDto = new ResponseDto<ParkDto>();

		try {
			if (bucket.tryConsume(1)) {
				ParkDto parkDto = parkingService.parks(licenceNumber);
				responseDto.setSuccess(true);
				responseDto.setData(parkDto);
			} else {
				responseDto.setError(new ErrorDto(Integer.toString(HttpStatus.TOO_MANY_REQUESTS.value()),
						HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase()));
			}
		} catch (ParkingException e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto(e.getStatus(), e.getError()));
		} catch (Exception e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto("501", " Error while parking vehicle"));
		}
		return responseDto;
	}

	/**
	 * This method is for checking whether Parking slot is empty or not
	 * 
	 * @param slotName
	 * @return ResponseDto<SlotDto> responseDto
	 */
	@ApiOperation(value = "Check status of Parking slot", response = SlotDto.class, notes = "Parking Slot name is required")
	@GetMapping(value = "/slot/{slotName}")
	ResponseDto<SlotDto> slot(@PathVariable String slotName) {

		ResponseDto<SlotDto> responseDto = new ResponseDto<SlotDto>();

		try {
			if (bucket.tryConsume(1)) {

				SlotDto slotDto = parkingService.slot(slotName);
				responseDto.setSuccess(true);
				responseDto.setData(slotDto);
			} else {
				responseDto.setError(new ErrorDto(Integer.toString(HttpStatus.TOO_MANY_REQUESTS.value()),
						HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase()));
			}
		} catch (ParkingException e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto(e.getStatus(), e.getError()));
		} catch (Exception e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto("502", " Error while geting slot details"));
		}
		return responseDto;
	}

	/**
	 * This method is for unparking the vehicle
	 * 
	 * @param licenceNumber
	 * @return ResponseDto<UnparkDto> responseDto
	 */
	@ApiOperation(value = "Unpark vehicle in parking", response = UnparkDto.class, notes = "License number is required")
	@DeleteMapping(value = "/unpark/{licenceNumber}")
	ResponseDto<UnparkDto> unpark(@PathVariable String licenceNumber) {

		ResponseDto<UnparkDto> responseDto = new ResponseDto<UnparkDto>();

		try {
			if (bucket.tryConsume(1)) {

				UnparkDto unparkDto = parkingService.unpark(licenceNumber);
				responseDto.setSuccess(true);
				responseDto.setData(unparkDto);
			} else {
				responseDto.setError(new ErrorDto(Integer.toString(HttpStatus.TOO_MANY_REQUESTS.value()),
						HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase()));
			}
		} catch (ParkingException e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto(e.getStatus(), e.getError()));
		} catch (Exception e) {
			responseDto.setSuccess(false);
			responseDto.setError(new ErrorDto("503", " Error while unparking vehicle"));
		}
		return responseDto;
	}

}
