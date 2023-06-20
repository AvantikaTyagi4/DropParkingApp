package com.drop.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drop.parking.entity.User;

/**
 * Repository for Interacting with User Entity
 * 
 * @author Avantika Tyagi
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String username);

}
