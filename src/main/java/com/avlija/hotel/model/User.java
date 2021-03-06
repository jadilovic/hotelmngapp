package com.avlija.hotel.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "app_user")
public class User {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="user_id")
 private long id;
 
 @Column(name = "email")
 private String email;
 
 @Column(name = "password")
 private String password;
 
 @Column(name = "first_name")
 private String firstName; 
 
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
 @JoinTable(name="user_role",
 			joinColumns=@JoinColumn(name="user_id"),
 			inverseJoinColumns=@JoinColumn(name="role_id"))
 private Set<Role> roles;

 @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
         cascade = CascadeType.ALL)
 private Set<NoteReservation> noteReservations;
 
 public User() {
	 
 }
 
 public User(String email, String password, String firstName, String last_name, String gender, String id_card_num,
		int age, int active, Set<Role> roles) {
	this.email = email;
	this.password = password;
	this.firstName = firstName;
	this.last_name = last_name;
	this.gender = gender;
	this.id_card_num = id_card_num;
	this.age = age;
	this.active = active;
	this.roles = roles;
}

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
public long getId() {
	return id;
}

/**
 * @param user_id the user_id to set
 */
public void setId(long id) {
	this.id = id;
}

/**
 * @return the first_name
 */
public String getFirstName() {
	return firstName;
}

/**
 * @param firstName the firstName to set
 */
public void setFirstName(String firstName) {
	this.firstName = firstName;
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

/**
 * @return the noteReservations
 */
public Set<NoteReservation> getNoteReservations() {
	return noteReservations;
}

/**
 * @param noteReservations the noteReservations to set
 */
public void setNoteReservations(Set<NoteReservation> noteReservations) {
	this.noteReservations = noteReservations;
}


}