package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
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

	
	
	
}
