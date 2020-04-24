package com.avlija.hotel.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.avlija.hotel.model.NoteReservation;

public interface NoteReservationRepository extends CrudRepository<NoteReservation, Long> {

	
	@Query("SELECT n FROM NoteReservation n WHERE n.checkIn <= ?2 AND n.checkOut >= ?1")
	List<NoteReservation> findByDateFromTo(java.sql.Date start, java.sql.Date end);
	
	// Before
	@Query("SELECT n FROM NoteReservation n WHERE n.checkIn < ?1")
	List<NoteReservation> findByDateBefore(Date before);
	
	@Query(value = "SELECT * FROM Notes n WHERE n.featured = 1",
            nativeQuery = true)
		List<NoteReservation> findByFeaturedNotesNative();
	
	@Query(value = "SELECT * FROM note_reservation WHERE (check_in <= ?2 AND check_out => ?1",
            nativeQuery = true)
		List<NoteReservation> findByDate(java.sql.Date start, java.sql.Date end);
	
}