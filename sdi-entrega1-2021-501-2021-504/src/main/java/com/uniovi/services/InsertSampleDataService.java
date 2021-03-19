package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;


public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("user", "Pedro", "Díaz");
		user1.setPassword("user");
		user1.setSaldo(100.0);
		user1.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user1);
		
		User user2 = new User("Juan@gmail.com", "Juan", "Díaz");
		user2.setPassword("user");
		user2.setSaldo(100.0);
		user2.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user2);
		
		User user3 = new User("pepe@hotmail.com", "Pepe", "Perez");
		user3.setPassword("user");
		user3.setSaldo(100.0);
		user3.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user3);
		
		User admin = new User("admin@email.es", "admin", "admin");
		admin.setPassword("admin");
		admin.setRole(rolesService.getRoles()[1]);
		usersService.addUser(admin);
	
	}
}