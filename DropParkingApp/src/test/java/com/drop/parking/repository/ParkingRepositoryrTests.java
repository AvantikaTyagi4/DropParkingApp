package com.drop.parking.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.drop.parking.entity.Parking;

/**
 * ParkingRepository Tests
 * 
 * @author Avantika Tyagi
 *
 */
@ExtendWith(MockitoExtension.class)
public class ParkingRepositoryrTests {

	@Mock
	private ParkingRepository parkingRepository;
	
	private List<Parking> parkings;
	private Parking parking;
	
	@BeforeEach
	void setup() {
		parkings = new ArrayList<>();
		
		parking = new Parking(1L, "A", "AB12EZ1234");
		Parking parking1 = new Parking(2L, "B", "CD14KL7894");
		
		parkings.add(parking1);
		parkings.add(parking);
	}
	
	/**
	 * Unit test for findByParkingSlot method
	 * 
	 */
	@Test
	void findByParkingSlot() {
		when(parkingRepository.findByParkingSlot("A")).thenReturn(parking);
		assertThat(parkingRepository.findByParkingSlot("A")).isEqualTo(parking);
		verify(parkingRepository).findByParkingSlot("A");
	}
	
	/**
	 * Unit test for findByLicenceNumber method
	 * 
	 */
	@Test
	void findByLicenceNumber() {
		when(parkingRepository.findByLicenceNumber("AB12EZ1234")).thenReturn(parking);
		assertThat(parkingRepository.findByLicenceNumber("AB12EZ1234")).isEqualTo(parking);
		verify(parkingRepository).findByLicenceNumber("AB12EZ1234");
	}
	
	@AfterEach
	void teardown() {
		verifyNoMoreInteractions(parkingRepository);
	}
}
