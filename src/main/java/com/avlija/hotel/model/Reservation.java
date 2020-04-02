package com.avlija.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="room")
public class Reservation {
 
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 @Column(name="reservation_id")
 private int reservationId;
 
 @ManyToOne
 @JoinColumn(name ="FK_UserId")
 private User user;
 
 @ManyToOne
 @JoinColumn(name ="FK_RoomNum")
 private Room room;
 
 @Column(name="check_in")
 private int checkIn;
 
 @Column(name="check_out")
 private int checkOut;
 
 public Reservation() {
	 
 }

public Reservation(User user, Room room, int checkIn, int checkOut) {
	this.user = user;
	this.room = room;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
}

/**
 * @return the reservationId
 */
public int getReservationId() {
	return reservationId;
}

/**
 * @param reservationId the reservationId to set
 */
public void setReservationId(int reservationId) {
	this.reservationId = reservationId;
}

/**
 * @return the user
 */
public User getUser() {
	return user;
}

/**
 * @param user the user to set
 */
public void setUser(User user) {
	this.user = user;
}

/**
 * @return the room
 */
public Room getRoom() {
	return room;
}

/**
 * @param room the room to set
 */
public void setRoom(Room room) {
	this.room = room;
}

/**
 * @return the checkIn
 */
public int getCheckIn() {
	return checkIn;
}

/**
 * @param checkIn the checkIn to set
 */
public void setCheckIn(int checkIn) {
	this.checkIn = checkIn;
}

/**
 * @return the checkOut
 */
public int getCheckOut() {
	return checkOut;
}

/**
 * @param checkOut the checkOut to set
 */
public void setCheckOut(int checkOut) {
	this.checkOut = checkOut;
}

}