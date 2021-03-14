package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User user1 = new User("user", "Pedro", "Díaz");
		user1.setPassword("user");
		user1.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user1);
		
		User admin = new User("admin@email.es", "admin", "admin");
		admin.setPassword("admin");
		admin.setRole(rolesService.getRoles()[1]);
		usersService.addUser(admin);
	
	}
}