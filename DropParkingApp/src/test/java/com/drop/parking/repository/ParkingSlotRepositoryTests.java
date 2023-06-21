package com.drop.parking.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.drop.parking.entity.ParkingSlot;

/**
 * Tests for ParkingSlotRepository
 * 
 * @author Avantika Tyagi
 *
 */
@ExtendWith(MockitoExtension.class)
public class ParkingSlotRepositoryTests {

	@Mock
	private ParkingSlotRepository parkingSlotRepository;

	private List<ParkingSlot> parkingSlots;

	private ParkingSlot parkingSlot;
	private ParkingSlot parkingSlot1;

	@BeforeEach
	void setup() {
		parkingSlots = new ArrayList<>();

		parkingSlot = new ParkingSlot(1L, "A");
		parkingSlot1 = new ParkingSlot(2L, "B");

		parkingSlots.add(parkingSlot1);
		parkingSlots.add(parkingSlot);

	}

	/**
	 * Unit test for findByparkingSlotNameNotIn method
	 */
	@Test
	void findByparkingSlotNameNotIn() {
		List<ParkingSlot> availableList = new ArrayList<>();
		availableList.add(parkingSlot1);
		when(parkingSlotRepository.findByparkingSlotNameNotIn(Stream.of("A").collect(Collectors.toList())))
				.thenReturn(availableList);
		assertThat(parkingSlotRepository.findByparkingSlotNameNotIn(Stream.of("A").collect(Collectors.toList())))
				.isEqualTo(availableList);
		verify(parkingSlotRepository).findByparkingSlotNameNotIn(Stream.of("A").collect(Collectors.toList()));
	}

	/**
	 * Unit test for findByParkingSlotName method
	 */
	@Test
	void findByParkingSlotName() {
		when(parkingSlotRepository.findByParkingSlotName("A")).thenReturn(parkingSlot);
		assertThat(parkingSlotRepository.findByParkingSlotName("A")).isEqualTo(parkingSlot);
		verify(parkingSlotRepository).findByParkingSlotName("A");
	}

	@AfterEach
	void tearDown() {
		verifyNoMoreInteractions(parkingSlotRepository);
	}
}
