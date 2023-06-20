package com.drop.parking.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.drop.parking.dto.AuthRequest;
import com.drop.parking.dto.ParkDto;
import com.drop.parking.dto.SlotDto;
import com.drop.parking.dto.UnparkDto;
import com.drop.parking.dto.UserDto;
import com.drop.parking.entity.Parking;
import com.drop.parking.entity.ParkingSlot;
import com.drop.parking.exceptions.ParkingException;
import com.drop.parking.repository.ParkingRepository;
import com.drop.parking.repository.ParkingSlotRepository;
import com.drop.parking.service.ParkingService;
import com.drop.parking.util.JwtUtil;

/**
 * Service Implementation
 * 
 * @author Avantika Tyagi
 *
 */
@Service
public class ParkingServiceImpl implements ParkingService {

	@Autowired
	private ParkingSlotRepository parkingSlotRepository;

	@Autowired
	private ParkingRepository parkingRepository;
	
	@Autowired 
	JwtUtil jwtUtil;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Value("${parking.lot.size}")
	private Integer parkingSlotSize;

	@Override
	public ParkDto parks(String licenceNumber) {
		List<Parking> parkingData = parkingRepository.findAll();

		if (!parkingData.isEmpty())
			if (parkingData.size() < parkingSlotSize) {
				List<String> bookedSlots = new ArrayList<>();
				for (Parking parking : parkingData) {
					if(parking.getLicenceNumber() == licenceNumber) {
						throw new ParkingException("407", "Car already parked");
					}
					bookedSlots.add(parking.getParkingSlot());
				}
				List<ParkingSlot> availableSlots = parkingSlotRepository.findByparkingSlotNameNotIn(bookedSlots);

				Parking parking = new Parking();
				parking.setLicenceNumber(licenceNumber);
				parking.setParkingSlot(availableSlots.stream().findFirst().get().getParkingSlotName());

				parkingRepository.save(parking);

				ParkDto parkDto = new ParkDto();
				parkDto.setLicenceNumber(licenceNumber);
				parkDto.setParkingSlot(parking.getParkingSlot());

				return parkDto;
			} else {
				throw new ParkingException("403", "No Empty Parking Slot available");
			}
		else {
			Parking parking = new Parking();
			parking.setLicenceNumber(licenceNumber);
			parking.setParkingSlot("A");
			parkingRepository.save(parking);

			ParkDto parkDto = new ParkDto();
			parkDto.setLicenceNumber(licenceNumber);
			parkDto.setParkingSlot(parking.getParkingSlot());

			return parkDto;
		}

	}

	@Override
	public SlotDto slot(String slotName) {
		ParkingSlot parkingSlot = parkingSlotRepository.findByParkingSlotName(slotName);
		if (parkingSlot != null) {

			SlotDto slotDto = new SlotDto();
			slotDto.setParkingSlot(slotName);
			Parking parking = parkingRepository.findByParkingSlot(slotName);

			if (parking != null) {
				slotDto.setSlotStatus("Occupied");
				slotDto.setLicenceNumber(parking.getLicenceNumber());
			} else {
				slotDto.setSlotStatus("Empty");
			}
			return slotDto;
		} else {
			throw new ParkingException("405", "Invalid Parking Slot");
		}

	}

	@Override
	public UnparkDto unpark(String licenceNumber) {
		Parking parking = parkingRepository.findByLicenceNumber(licenceNumber);
		if (parking != null) {
			parkingRepository.deleteById(parking.getParkingId());
			UnparkDto unparkDto = new UnparkDto();
			unparkDto.setLicenceNumber(licenceNumber);
			unparkDto.setParkingSlot(parking.getParkingSlot());
			return unparkDto;
		} else {
			throw new ParkingException("406", "Car not Found.");
		}

	}

	@Override
	public UserDto login(AuthRequest authRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));
		}catch(Exception e) {
			throw new ParkingException("401", "Invalid username/password");
		}
		String token = jwtUtil.generateToken(authRequest.getUserName());
		UserDto userDto = new UserDto();
		userDto.setAuthenticationToken(token);
		userDto.setUsername(authRequest.getUserName());
		return userDto;
	}

}
