package com.avlija.hotel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="app_role")
public class Role {
 
 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 @Column(name="role_id")
 private int id;
 
 @Column(name="role_name")
 private String role;
 
 @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
 private Set<User> users = new HashSet<>();


 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getRole() {
  return role;
 }

 public void setRole(String role) {
  this.role = role;
 }
}