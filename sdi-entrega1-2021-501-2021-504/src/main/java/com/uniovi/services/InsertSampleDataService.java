package com.uniovi.services;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private OffersService offersService;

	
	
	
	
	@PostConstruct
	public void init() {
		
		
		// Usuario 1
		User user1 = new User("user", "Pedro", "Díaz");
		user1.setPassword("user");
		user1.setSaldo(100.0);
		user1.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user1);
		// Ofertas usuario 1
		Offer of1 = new Offer("Triciclo", "En buen estado", LocalDate.now(), 50, user1);
		offersService.addOffer(of1);
		Offer of2 = new Offer("Boligrafo", "De regalo entrada para la final de la champions", LocalDate.now(), 110,
				user1);
		offersService.addOffer(of2);
		Offer of3 = new Offer("Movil", "Alcatel", LocalDate.now(), 30, user1);
		offersService.addOffer(of3);

		// Usuario 2
		User user2 = new User("Juan@gmail.com", "Juan", "Díaz");
		user2.setPassword("123456");
		user2.setSaldo(200.0);
		user2.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user2);
		// Ofertas usuario 2
		Offer of4 = new Offer("Opel corsa", "Lo vendo", LocalDate.now(), 80, user2);
		offersService.addOffer(of4);
		Offer of5 = new Offer("PC", "Con intel core 7", LocalDate.now(), 70, user2);
		offersService.addOffer(of5);
		Offer of6 = new Offer("Piedra", "Durisima", LocalDate.now(), 30, user2);
		offersService.addOffer(of6);

		// Usuario 3
		User user3 = new User("pepe@hotmail.com", "Pepe", "Perez");
		user3.setPassword("123456");
		user3.setSaldo(100.0);
		user3.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user3);
		// Ofertas usuario3
		Offer of7 = new Offer("Gorra de alonso ", "Firmada por el propio Alonso", LocalDate.now(), 999, user3);
		offersService.addOffer(of7);
		Offer of8 = new Offer("Portatil", "De marca HP", LocalDate.now(), 90, user3);
		offersService.addOffer(of8);
		Offer of9 = new Offer("Hoja de papel", "Es blanca ", LocalDate.now(), 10, user3);
		offersService.addOffer(of9);

		// Usuario 4
		User user4 = new User("alex@hotmail.com", "Alex", "Caso");
		user4.setPassword("123456");
		user4.setSaldo(500.0);
		user4.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user4);

		// Ofertas usuario4
		Offer of10 = new Offer("Camara digital ", "Hace muy buenas fotos", LocalDate.now(), 220, user4);
		offersService.addOffer(of10);
		Offer of11 = new Offer("Tablet", "De marca Samsung", LocalDate.now(), 80, user4);
		offersService.addOffer(of11);
		Offer of12 = new Offer("Nintendo", "Esta rota ", LocalDate.now(), 40, user4);
		offersService.addOffer(of12);

		// Usuario 5
		User user5 = new User("eric@hotmail.com", "Eric", "Almeda");
		user5.setPassword("123456");
		user5.setSaldo(500.0);
		user5.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user5);
		// Ofertas usuario 5
		Offer of13 = new Offer("Ford fiesta ", "65 caballos", LocalDate.now(), 500, user5);
		Offer of14 = new Offer("Playeros", "De marca nike", LocalDate.now(), 80, user5);
		Offer of15 = new Offer("Pelota de baloncesto", "Esta desinchada ", LocalDate.now(), 5, user5);
		offersService.addOffer(of13);
		offersService.addOffer(of14);
		offersService.addOffer(of15);

		// Usuario 6
		User user6 = new User("edward@hotmail.com", "Edward", "Rolando");
		user6.setPassword("123456");
		user6.setSaldo(500.0);
		user6.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user6);
		// Ofertas usuario6
		Offer of16 = new Offer("Lampara", "Grande para el salón", LocalDate.now(), 50, user6);
		Offer of17 = new Offer("Aifon", "Es el 7", LocalDate.now(), 10, user6);
		Offer of18 = new Offer("Dinero", "Vendo dinero", LocalDate.now(), 2, user6);

		offersService.addOffer(of16);
		offersService.addOffer(of17);
		offersService.addOffer(of18);

		// Compras de ofertas
		offersService.buyOffer(of5.getId(), user1);
		offersService.buyOffer(of6.getId(), user1);

		offersService.buyOffer(of1.getId(), user2);
		offersService.buyOffer(of2.getId(), user2);

		offersService.buyOffer(of3.getId(), user3);
		offersService.buyOffer(of4.getId(), user3);

		offersService.buyOffer(of14.getId(), user4);
		offersService.buyOffer(of18.getId(), user4);

		offersService.buyOffer(of16.getId(), user5);
		offersService.buyOffer(of17.getId(), user5);

		offersService.buyOffer(of12.getId(), user6);
		offersService.buyOffer(of13.getId(), user6);

		User admin = new User("admin@email.es", "admin", "admin");
		admin.setPassword("admin");
		admin.setRole(rolesService.getRoles()[1]);
		usersService.addUser(admin);

	}
}