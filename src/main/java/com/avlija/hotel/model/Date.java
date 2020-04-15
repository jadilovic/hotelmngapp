package com.avlija.hotel.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "dates")
public class Date implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
    //@ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "room_id", nullable = false)
	//private Room room;
	
	@Column(name = "check_in")
	private LocalDate start;
	
	@Column(name = "check_out")
	private LocalDate end;
	
    //@OneToOne(fetch = FetchType.LAZY, optional = false)
   // @JoinColumn(name = "user_id", nullable = false)
	// private User user;
    
    @ManyToMany(mappedBy = "dates", fetch = FetchType.LAZY)
    private Set<Reservation> reservations = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id")
    private Room room;


	public Date() {
    	
    }

	public Date(LocalDate checkIn, LocalDate checkOut, Room room) {
		this.start = checkIn;
		this.end = checkOut;
		this.room = room;
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
	/**
	 * @return the start
	 */
	public LocalDate getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(LocalDate start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public LocalDate getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(LocalDate end) {
		this.end = end;
	}

	/**
	 * @return the reservations
	 */
	public Set<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * @param reservations the reservations to set
	 */
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	
}