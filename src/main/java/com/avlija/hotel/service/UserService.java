package com.avlija.hotel.service;

import com.avlija.hotel.model.User;

public interface UserService {
  
 public User findUserByEmail(String email);
 
 public void saveUser(User user);
}