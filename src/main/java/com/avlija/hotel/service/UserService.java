package com.avlija.hotel.service;

import java.util.List;

import com.avlija.hotel.model.User;

public interface UserService {
  
 public User findUserByEmail(String email);
 
 public void saveUser(User user);
 
 public void updateUser(User user);
 
 public List<User> finaAllUsers();
 
 public User findUserById(long id);
 
 public List<User> findAllActiveUsers(int active);

}