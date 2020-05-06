package com.avlija.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.avlija.hotel.model.Role;
import com.avlija.hotel.model.User;
import com.avlija.hotel.repository.RoleRespository;
import com.avlija.hotel.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
 
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 private RoleRespository roleRespository;
 
 @Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;

 @Override
 public User findUserByEmail(String email) {
  return userRepository.findByEmail(email);
 }

 @Override
 public void saveUser(User user) {
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  user.setActive(1);
  Role userRole = roleRespository.findByRole(user.getRole());
  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
  userRepository.save(user);
 }

@Override
public List<User> finaAllUsers() {
	
	return (List<User>) userRepository.findAll();
}

@Override
public User findUserById(long id) {
	// TODO Auto-generated method stub
	return userRepository.findById(id).get();
}

@Override
public List<User> findAllActiveUsers(int active) {

	return userRepository.findByActive(active);
}

@Override
public void updateUser(User user) {
	  userRepository.save(user);
}

@Override
public User findByUserFirstName(String firstName) {
	return userRepository.findByFirstName(firstName);
}

}