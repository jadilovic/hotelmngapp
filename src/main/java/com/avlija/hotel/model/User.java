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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "app_user")
public class User {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="user_id")
 private int id;
 
 @Column(name = "email")
 private String email;
 
 @Column(name = "password")
 private String password;
 
 @Column(name = "first_name")
 private String first_name; 
 
 @Column(name = "last_name")
 private String last_name;
 
 @Column(name = "gender")
 private String gender; 
 
 @Column(name = "id_card_num")
 private String id_card_num;
 
 @Column(name = "age")
 private int age;
 
 @Column(name = "active")
 private int active;
 
 @Transient
 private String role;
 
 @ManyToMany(cascade=CascadeType.ALL)
 @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
 private Set<Role> roles;
 
 @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
 Set<Reservation> reservations = new HashSet<Reservation>();
 
 
 /**
 * @return the gender
 */
public String getGender() {
	return gender;
}

/**
 * @param gender the gender to set
 */
public void setGender(String gender) {
	this.gender = gender;
}

/**
 * @return the age
 */
public int getAge() {
	return age;
}

/**
 * @param age the age to set
 */
public void setAge(int age) {
	this.age = age;
}

/**
 * @return the user_id
 */
public int getId() {
	return id;
}

/**
 * @param user_id the user_id to set
 */
public void setId(int id) {
	this.id = id;
}

/**
 * @return the first_name
 */
public String getFirst_name() {
	return first_name;
}

/**
 * @param first_name the first_name to set
 */
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

/**
 * @return the last_name
 */
public String getLast_name() {
	return last_name;
}

/**
 * @param last_name the last_name to set
 */
public void setLast_name(String last_name) {
	this.last_name = last_name;
}

/**
 * @return the id_card_num
 */
public String getId_card_num() {
	return id_card_num;
}

/**
 * @param id_card_num the id_card_num to set
 */
public void setId_card_num(String id_card_num) {
	this.id_card_num = id_card_num;
}

public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public int getActive() {
  return active;
 }

 public void setActive(int active) {
  this.active = active;
 }

 public Set<Role> getRoles() {
  return roles;
 }

 public void setRoles(Set<Role> roles) {
  this.roles = roles;
 }

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}
}