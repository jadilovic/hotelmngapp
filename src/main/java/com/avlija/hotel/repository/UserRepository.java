package com.avlija.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avlija.hotel.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
 
 User findByEmail(String email);
 
 List<User> findByActive(int active);
 
 User findByFirstName(String firstName);
}