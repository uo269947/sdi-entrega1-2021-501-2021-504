package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;
	
	public void addOffer(Offer offer) {
		offersRepository.save(offer);
	}

	public Iterable<Offer> findAll() {
		return offersRepository.findAll();
		
	}

	public List<Offer> getMyOffers(User user) {
		List<Offer> offers = new ArrayList<Offer>();
		offersRepository.findMyOffers(user).forEach(offers::add);
		return offers;
	}

	public List<Offer> getMyOffersBySearch(User user, String searchText) {
		List<Offer> offers = new ArrayList<Offer>();
		searchText = "%"+searchText+"%";
		offers = offersRepository.searchByDescriptionAndUser(searchText, user);
		return offers;
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}
}
