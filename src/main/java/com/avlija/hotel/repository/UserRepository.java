package com.avlija.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avlija.hotel.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
 
 User findByEmail(String email);
}