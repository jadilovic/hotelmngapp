package com.avlija.hotel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Room {
 
 @Id
 @Column(name="room_num")
 private int num;
 
 @ManyToOne
 @JoinColumn(name ="FK_RoomTypeId")
 private RoomType roomType;
 
 @OneToMany(mappedBy="room", cascade = CascadeType.ALL)
 Set<Reservation> reservations = new HashSet<Reservation>();
 
 @Column(name="available")
 private int available;
 
 public Room() {
	 
 }

public Room(int num, RoomType roomType, int available) {
	this.num = num;
	this.roomType = roomType;
	this.available = available;
}

/**
 * @return the num
 */
public int getNum() {
	return num;
}

/**
 * @param num the num to set
 */
public void setNum(int num) {
	this.num = num;
}

/**
 * @return the roomType
 */
public RoomType getRoomType() {
	return roomType;
}

/**
 * @param roomType the roomType to set
 */
public void setRoomType(RoomType roomType) {
	this.roomType = roomType;
}

/**
 * @return the available
 */
public int getAvailable() {
	return available;
}

/**
 * @param available the available to set
 */
public void setAvailable(int available) {
	this.available = available;
}
 


}