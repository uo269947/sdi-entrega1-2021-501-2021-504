package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

public interface OffersRepository  extends CrudRepository<Offer, Long>{

	@Query("SELECT r FROM Offer r WHERE r.user = ?1 ORDER BY r.id ASC ")
	List<Offer> findMyOffers(User user);

	@Query ("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1) OR LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1)) AND r.user = ?2 ")
	List<Offer> searchByDescriptionAndUser(String searchText, User user);

	@Query("SELECT r FROM Offer r WHERE r.user <> ?1  ORDER BY r.id ASC ")
	List<Offer> findOtherOffers(User user);

	@Query ("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1) OR LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1)) AND r.user <> ?2 AND r.buyer is null")
	List<Offer> searchOthersByDescriptionAndUser(String searchText, User user);

	@Query ("SELECT r FROM Offer r WHERE (LOWER(r.title) LIKE LOWER(?1) OR LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.user.name) LIKE LOWER(?1)) AND r.user <> ?2 AND r.buyer = ?1")
	List<Offer> searchBoughtByDescriptionAndUser(String searchText, User user);

	@Query("SELECT r FROM Offer r WHERE r.user <> ?1 AND r.buyer = ?1 ORDER BY r.id ASC ")
	List<Offer> findBoughtOffers(User user);



}
