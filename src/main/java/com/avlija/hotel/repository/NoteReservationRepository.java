package com.avlija.hotel.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.avlija.hotel.model.NoteReservation;

public interface NoteReservationRepository extends CrudRepository<NoteReservation, Long> {

	
	@Query("SELECT n FROM NoteReservation n WHERE n.checkIn >= ?1 AND n.checkOut <= ?2")
	List<NoteReservation> findByDateFromTo(java.sql.Date start, java.sql.Date end);
	
	// Before
	@Query("SELECT n FROM NoteReservation n WHERE n.checkIn < ?1")
	List<NoteReservation> findByDateBefore(Date before);
}