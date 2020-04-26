package com.avlija.hotel.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    
    @Column(name="reservation_days")
    private int resDays;
    
    @Column(name="total_cost")
    private double totalCost;
    
    @Column(name="services_cost")
    private double servicesCost;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id")
    private Room room;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "note_reservation_service",
            joinColumns = {
                    @JoinColumn(name = "reservation_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "service_id", referencedColumnName = "service_id",
                            nullable = false, updatable = false)})
    private Set<AddService> services = new HashSet<>();

    
    public NoteReservation() {
    }

    public NoteReservation(boolean booked, Date created, java.sql.Date checkIn, java.sql.Date checkOut, 
    						Room room, User user, int resDays, double totalCost, double servicesCost) {
		this.booked = booked;
		this.created = created;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.room = room;
		this.user = user;
		this.resDays = resDays;
		this.totalCost = totalCost;
		this.servicesCost = servicesCost;
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

	/**
	 * @return the resDays
	 */
	public int getResDays() {
		return resDays;
	}

	/**
	 * @param resDays the resDays to set
	 */
	public void setResDays(int resDays) {
		this.resDays = resDays;
	}

	/**
	 * @return the totalCost
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost the totalCost to set
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * @return the servicesCost
	 */
	public double getServicesCost() {
		return servicesCost;
	}

	/**
	 * @param servicesCost the servicesCost to set
	 */
	public void setServicesCost(double servicesCost) {
		this.servicesCost = servicesCost;
	}

	/**
	 * @return the services
	 */
	public Set<AddService> getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(Set<AddService> services) {
		this.services = services;
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