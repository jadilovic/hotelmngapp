package com.avlija.hotel.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="room")
public class Room  implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
 
 @Column(name="room_num")
 private int num;
 
 
// @Transient
// private String roomType;
 
 @ManyToOne
 @JoinColumn(name ="FK_RoomTypeId")
 private RoomType roomType;
 
 
 //@OneToOne(mappedBy = "room", fetch = FetchType.LAZY,
//         cascade = CascadeType.ALL)
// private Reservation reservation;
 
 @OneToMany(mappedBy = "room", fetch = FetchType.LAZY,
         cascade = CascadeType.ALL)
 private Set<Date> dates;

/**
 * @return the dates
 */
public Set<Date> getDates() {
	return dates;
}

/**
 * @param dates the dates to set
 */
public void setDates(Set<Date> dates) {
	this.dates = dates;
}

public Room() {
	 
 }

public Room(int num, RoomType roomType) {
	this.num = num;
	this.roomType = roomType;
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
 * @return the id
 */
public long getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(long id) {
	this.id = id;
}

@Override
public String toString() {
	return "Room number: " + num + ", Type: " + roomType.getType();
}
}