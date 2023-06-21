package com.drop.parking.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.drop.parking.dto.AuthRequest;
import com.drop.parking.dto.ParkDto;
import com.drop.parking.dto.ResponseDto;
import com.drop.parking.dto.SlotDto;
import com.drop.parking.dto.UnparkDto;
import com.drop.parking.dto.UserDto;
import com.drop.parking.service.ParkingService;

/**
 * Tests for Parking Controller
 * 
 * @author Avantika Tyagi
 *
 */
@ExtendWith(MockitoExtension.class)
public class ParkingControllerTests {

	@InjectMocks
	private ParkingController parkingController;

	@Mock
	private ParkingService parkingService;

	private ParkDto parkDto;
	private SlotDto slotDto;
	private UnparkDto unparkDto;

	@BeforeEach
	void setup() {

		parkDto = new ParkDto("A", "AB12EZ1234");
		slotDto = new SlotDto("A", "AB12EZ1234", "Occupied");
		unparkDto = new UnparkDto("A", "AB12EZ1234");
	}

	/**
	 * Unit test for login method
	 */
	@Test
	void login() {

		AuthRequest authRequest = new AuthRequest("user1", "user123#");
		UserDto userDto = new UserDto("user1", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNrQG1haWwuY29tIiwiZXhwIjoxN");

		when(parkingService.login(authRequest)).thenReturn(userDto);
		assertThat(parkingController.login(authRequest)).isInstanceOfAny(ResponseDto.class);
		verify(parkingService).login(authRequest);
	}

	/**
	 * Unit test for park method
	 */
	@Test
	void park() {
		when(parkingService.parks("AB12EZ1234")).thenReturn(parkDto);
		assertThat(parkingController.park("AB12EZ1234")).isInstanceOfAny(ResponseDto.class);
		verify(parkingService).parks("AB12EZ1234");
	}

	/**
	 * Unit test for slot method
	 */
	@Test
	void slot() {
		when(parkingService.slot("A")).thenReturn(slotDto);
		assertThat(parkingController.slot("A")).isInstanceOfAny(ResponseDto.class);
		verify(parkingService).slot("A");

	}

	/**
	 * Unit test for unpark method
	 */
	@Test
	void unpark() {
		when(parkingService.unpark("AB12EZ1234")).thenReturn(unparkDto);
		assertThat(parkingController.unpark("AB12EZ1234")).isInstanceOfAny(ResponseDto.class);
		verify(parkingService).unpark("AB12EZ1234");

	}

	@AfterEach
	void tearDown() {
		verifyNoMoreInteractions(parkingService);
	}
}
