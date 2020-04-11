package com.avlija.hotel.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservations")
public class Reservation implements Serializable {
 
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 @Column(name="id")
 private long id;
 
 @Column(name="reservation_date")
 private Date resDate;
 
 @Column(name="reservation_days")
 private int resDays;
 
 @Column(name="total_cost")
 private double totalCost;
 
 @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
 @JoinTable(name = "reservation_date",
         joinColumns = {
                 @JoinColumn(name = "reservation_id", referencedColumnName = "id",
                         nullable = false, updatable = false)},
         inverseJoinColumns = {
                 @JoinColumn(name = "date_id", referencedColumnName = "id",
                         nullable = false, updatable = false)})
 private Set<com.avlija.hotel.model.Date> dates = new HashSet<>();
 
 @ManyToOne(fetch = FetchType.LAZY, optional = false)
 @JoinColumn(name = "user_id", nullable = false)
 private User user;
 
 @OneToOne(mappedBy = "reservation", fetch = FetchType.LAZY,
         cascade = CascadeType.ALL)
 private Room room;
 
 public Reservation() {
	 
 }

public Reservation(Date resDate, int resDays, double totalCost, User user, Room room) {
	this.resDate = resDate;
	this.resDays = resDays;
	this.totalCost = totalCost;
	this.user = user;
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
 * @return the reservationId
 */
public long getId() {
	return id;
}

/**
 * @param reservationId the reservationId to set
 */
public void setId(long id) {
	this.id = id;
}

/**
 * @return the resDate
 */
public Date getResDate() {
	return resDate;
}

/**
 * @param resDate the resDate to set
 */
public void setResDate(Date resDate) {
	this.resDate = resDate;
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
 * @return the dates
 */
public Set<com.avlija.hotel.model.Date> getDates() {
	return dates;
}

/**
 * @param dates the dates to set
 */
public void setDates(Set<com.avlija.hotel.model.Date> dates) {
	this.dates = dates;
}

}