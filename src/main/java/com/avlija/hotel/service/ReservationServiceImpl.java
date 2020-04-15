package com.avlija.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.avlija.hotel.model.Reservation;
import com.avlija.hotel.repository.ReservationRepository;

import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {
 
 @Autowired
 private ReservationRepository reservationRepository;

@Override
public Reservation findReservationById(Long id) {
	return reservationRepository.findById(id).get();
}

@Override
public List<Reservation> findAllReservations() {
	return (List<Reservation>) reservationRepository.findAll();
}

@Override
public void saveReservation(Reservation reservation) {
	reservationRepository.save(reservation);
}

}
 