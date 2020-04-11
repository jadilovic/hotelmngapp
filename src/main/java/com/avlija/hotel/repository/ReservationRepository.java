package com.avlija.hotel.repository;


import org.springframework.data.repository.CrudRepository;

import com.avlija.hotel.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

}