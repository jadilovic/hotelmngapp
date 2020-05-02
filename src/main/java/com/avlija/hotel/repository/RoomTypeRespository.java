package com.avlija.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avlija.hotel.model.RoomType;

@Repository("roomTypeRepository")
public interface RoomTypeRespository extends JpaRepository<RoomType, Integer> {

 //RoomType findBy(String role);
}