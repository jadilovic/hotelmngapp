package com.avlija.hotel.service;


import java.util.List;

import com.avlija.hotel.model.Reservation;

public interface ReservationService {
 
 public Reservation findReservationById(Long id);
 
 public List<Reservation> findAllReservations();
 
 public void saveReservation(Reservation reservation);
 
}