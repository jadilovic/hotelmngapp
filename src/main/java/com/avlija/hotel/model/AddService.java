package com.avlija.hotel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="add_services")
public class AddService {

@Id
 @Column(name="service_id")
 private int id;
 
 @Column(name="service_name")
 private String name;
 
 @Column(name="service_cost")
 private double cost;
 
 public AddService() {
	 
 }

public AddService(int id, String name, double cost) {
	this.id = id;
	this.name = name;
	this.cost = cost;
}

/**
 * @return the id
 */
public int getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}

/**
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
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

@Override
public String toString() {
	return name + ", Cost: " + cost + "KM";
}

}