package com.avlija.hotel.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="invoice")
public class Invoice implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
    private Date created;

    private double totalHotelCost;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notereservation_id")
    private NoteReservation noteReservation;
    
    public Invoice() {
    }



	public Invoice(Date created, double totalHotelCost) {
		this.created = created;
		this.totalHotelCost = totalHotelCost;
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
	 * @return the totalHotelCost
	 */
	public double getTotalHotelCost() {
		return totalHotelCost;
	}



	/**
	 * @param totalHotelCost the totalHotelCost to set
	 */
	public void setTotalHotelCost(double totalHotelCost) {
		this.totalHotelCost = totalHotelCost;
	}



	/**
	 * @return the noteReservation
	 */
	public NoteReservation getNoteReservation() {
		return noteReservation;
	}



	/**
	 * @param noteReservation the noteReservation to set
	 */
	public void setNoteReservation(NoteReservation noteReservation) {
		this.noteReservation = noteReservation;
	}


}