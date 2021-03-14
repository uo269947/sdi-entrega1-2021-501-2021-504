package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
	
	@Query("SELECT r FROM User r WHERE r.name = ?1 OR r.lastName = ?1")
	List<User> findByNameOrSurname(String text);
	
}
