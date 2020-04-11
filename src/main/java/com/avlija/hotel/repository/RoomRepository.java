package com.avlija.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avlija.hotel.model.Room;

@Repository("roomRepository")
public interface RoomRepository extends JpaRepository<Room, Long> {
 
 Room findByNum(int num);
}