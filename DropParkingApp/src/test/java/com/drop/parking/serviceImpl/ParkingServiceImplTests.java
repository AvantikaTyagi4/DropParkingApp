package com.drop.parking.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import com.drop.parking.dto.AuthRequest;
import com.drop.parking.dto.ParkDto;
import com.drop.parking.dto.SlotDto;
import com.drop.parking.dto.UnparkDto;
import com.drop.parking.dto.UserDto;
import com.drop.parking.entity.Parking;
import com.drop.parking.entity.ParkingSlot;
import com.drop.parking.repository.ParkingRepository;
import com.drop.parking.repository.ParkingSlotRepository;
import com.drop.parking.serviceimpl.ParkingServiceImpl;
import com.drop.parking.util.JwtUtil;

/**
 * Tests for ParkingServiceImpl
 * 
 * @author Avantika Tyagi
 *
 */
@ExtendWith(MockitoExtension.class)
public class ParkingServiceImplTests {

	@InjectMocks
	private ParkingServiceImpl parkingServiceImpl;

	@Mock
	private ParkingSlotRepository parkingSlotRepository;

	@Mock
	private ParkingRepository parkingRepository;

	@Mock
	JwtUtil jwtUtil;

	@Mock
	AuthenticationManager authenticationManager;

	private List<Parking> parkings;
	private Parking parking;
	private List<ParkingSlot> parkingSlots;

	private ParkingSlot parkingSlot;
	private ParkingSlot parkingSlot1;
	private SlotDto slotDto;
	private UnparkDto unparkDto;

	@BeforeEach
	void setup() {
		parkings = new ArrayList<>();

		parking = new Parking(1L, "A", "AB12EZ1234");

		parkings.add(parking);
		parkingSlots = new ArrayList<>();

		parkingSlot = new ParkingSlot(1L, "A");
		parkingSlot1 = new ParkingSlot(2L, "B");

		parkingSlots.add(parkingSlot1);
		parkingSlots.add(parkingSlot);
		new ParkDto("A", "AB12EZ1234");
		slotDto = new SlotDto("A", "AB12EZ1234", "Occupied");
		unparkDto = new UnparkDto("A", "AB12EZ1234");
	}

	/**
	 * Unit test for slot method
	 * 
	 */
	@Test
	void slot() {
		when(parkingSlotRepository.findByParkingSlotName("A")).thenReturn(parkingSlot);
		when(parkingRepository.findByParkingSlot("A")).thenReturn(parking);
		assertThat(parkingServiceImpl.slot("A")).isEqualTo(slotDto);
		verify(parkingSlotRepository).findByParkingSlotName("A");
		verify(parkingRepository).findByParkingSlot("A");
	}

	/**
	 * Unit test for unpark method
	 * 
	 */
	@Test
	void unpark() {
		when(parkingRepository.findByLicenceNumber("AB12EZ1234")).thenReturn(parking);
		doNothing().when(parkingRepository).deleteById(1L);
		assertThat(parkingServiceImpl.unpark("AB12EZ1234")).isEqualTo(unparkDto);
		verify(parkingRepository).findByLicenceNumber("AB12EZ1234");
		verify(parkingRepository).deleteById(1L);
	}

	/**
	 * Unit test for login method
	 * 
	 */
	@Test
	void login() {
		AuthRequest authRequest = new AuthRequest("user1", "user123#");
		UserDto userDto = new UserDto("user1", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNrQG1haWwuY29tIiwiZXhwIjoxN");
		when(jwtUtil.generateToken(authRequest.getUserName())).thenReturn(userDto.getAuthenticationToken());
		assertThat(parkingServiceImpl.login(authRequest)).isEqualTo(userDto);
		verify(jwtUtil).generateToken(authRequest.getUserName());
	}

	@AfterEach
	void tearDown() {
		verifyNoMoreInteractions(parkingRepository, parkingSlotRepository, jwtUtil);
	}
}
