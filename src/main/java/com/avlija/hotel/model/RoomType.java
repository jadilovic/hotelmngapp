package com.avlija.hotel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="room_type")
public class RoomType {
 
 @Id
 @Column(name="type_id")
 private int id;
 
 @Column(name="type_name")
 private String type;
 
 @Column(name="type_cost")
 private double cost;
 
 @OneToMany(mappedBy="roomType", cascade = CascadeType.ALL)
 Set<Room> rooms = new HashSet<Room>();
 
 public RoomType() {
	 
 }

 public RoomType(int id, String type, double cost) {
	this.id = id;
	this.type = type;
	this.cost = cost;
}

public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

/**
 * @return the type
 */
public String getType() {
	return type;
}

/**
 * @param type the type to set
 */
public void setType(String type) {
	this.type = type;
}

/**
 * @return the cost
 */
public double getCost() {
	return cost;
}

/**
 * @param cost the cost to set
 */
public void setCost(double cost) {
	this.cost = cost;
}

}