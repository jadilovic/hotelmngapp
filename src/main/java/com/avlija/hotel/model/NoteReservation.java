package com.avlija.hotel.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="note_reservation")
public class NoteReservation implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean booked;
    private Date created;
    private java.sql.Date checkIn;
    private java.sql.Date checkOut;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id")
    private Room room;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public NoteReservation() {
    }

    public NoteReservation(boolean booked, Date created, java.sql.Date checkIn, java.sql.Date checkOut, 
    						Room room, User user) {
		this.booked = booked;
		this.created = created;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.room = room;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the booked
	 */
	public boolean isBooked() {
		return booked;
	}

	/**
	 * @param booked the booked to set
	 */
	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the checkIn
	 */
	public java.sql.Date getCheckIn() {
		return checkIn;
	}

	/**
	 * @param checkIn the checkIn to set
	 */
	public void setCheckIn(java.sql.Date checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * @return the checkOut
	 */
	public java.sql.Date getCheckOut() {
		return checkOut;
	}

	/**
	 * @param checkOut the checkOut to set
	 */
	public void setCheckOut(java.sql.Date checkOut) {
		this.checkOut = checkOut;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteReservation note = (NoteReservation) o;
        return id.equals(note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID=" + id +
                ", Room ID='" + room.getId() + '\'' +
                ", User ID='" + user.getId() + '\'' +
                ", Check IN=" + checkIn +
                ", Check OUT=" + checkOut +
                ", Created=" + created +
                ", Booked=" + booked +
                '}';
    }
}