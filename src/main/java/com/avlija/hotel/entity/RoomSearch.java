package com.avlija.hotel.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.avlija.hotel.model.LocalDateAttributeConverter;
import com.avlija.hotel.model.NoteReservation;
import com.avlija.hotel.model.Room;
import com.avlija.hotel.repository.NoteReservationRepository;
import com.avlija.hotel.repository.RoomRepository;

public class RoomSearch {
	

@Autowired
private RoomRepository roomRepository;

@Autowired
private NoteReservationRepository noteReservationRepository;

	private String inputFromDate;
	private String inputToDate;
	
	public RoomSearch() {
		
	}

	public RoomSearch(String inputFromDate, String inputToDate) {
		this.inputFromDate = inputFromDate;
		this.inputToDate = inputToDate;
	}

	public List<Room> searchRoomsByDate(){

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate In = LocalDate.parse(inputFromDate, formatter);
		 LocalDate Out = LocalDate.parse(inputToDate, formatter);
		// In = In.plusDays(1);
		// Out = Out.plusDays(1);
		 
		 System.out.println("TEST 1, TEST 1, TEST 1");
		 System.out.println("Check in: " + In.toString() + ", Check out: " + Out.toString());
		 
		 LocalDateAttributeConverter converter = new LocalDateAttributeConverter();
		 java.sql.Date start = converter.convertToDatabaseColumn(In);
		 java.sql.Date end = converter.convertToDatabaseColumn(Out);
		 
		 System.out.println("TEST 1.5, TEST 1.5, TEST 1.5");
		 System.out.println("Check in: " + start.toString() + ", Check out: " + end.toString());
		 
		 List<NoteReservation> reservations = new ArrayList<>();
		 reservations = noteReservationRepository.findByDateFromTo(start, end);
		 
		 List<Long> bookedRoomsIds = new ArrayList<>();
		 
		for(NoteReservation reservation: reservations) {
			bookedRoomsIds.add(reservation.getRoom().getId());
			 System.out.println("RESERVATION FROM TO DATES: Start:" + reservation.toString() + ", End: " + reservation.toString());
		 }

		 System.out.println("TEST 3, TEST 3, TEST 3");
		 List<Long> allRoomsIds = roomRepository.findAllIds();

		 System.out.println("TEST 4, TEST 4, TEST 4");

		 System.out.println("ALL ROOMS IDS: " + allRoomsIds.toString());
		 System.out.println("BOOKED ROOMS IDS: " + bookedRoomsIds.toString());
		 
		 // Removing booked IDs from all room IDs
		 	allRoomsIds.removeAll(bookedRoomsIds);
		 
		 System.out.println("AVAILABLE ROOMS IDS: " + allRoomsIds.toString());
		 
		 List<Room> availableRooms = new ArrayList<>();
		 for(long availableRoomId: allRoomsIds) {
			 availableRooms.add(roomRepository.findById(availableRoomId).get());
		 }
		 
		return availableRooms;
	}

	/**
	 * @return the inputFromDate
	 */
	public String getInputFromDate() {
		return inputFromDate;
	}

	/**
	 * @param inputFromDate the inputFromDate to set
	 */
	public void setInputFromDate(String inputFromDate) {
		this.inputFromDate = inputFromDate;
	}

	/**
	 * @return the inputToDate
	 */
	public String getInputToDate() {
		return inputToDate;
	}

	/**
	 * @param inputToDate the inputToDate to set
	 */
	public void setInputToDate(String inputToDate) {
		this.inputToDate = inputToDate;
	}

	
}
