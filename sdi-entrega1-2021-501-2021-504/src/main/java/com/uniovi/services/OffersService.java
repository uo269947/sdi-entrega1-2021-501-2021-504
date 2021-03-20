package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;

@Service
public class OffersService {

	@Autowired
	private OffersRepository offersRepository;
	
	
	@Autowired
	private UsersRepository usersRepository;
	
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

	public Page<Offer> getOtherOffers(Pageable pageable, User user) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offersRepository.findOtherOffers(pageable, user);
		return offers;
	}

	public Page<Offer> getOtherOffersBySearch(Pageable pageable, User user, String searchText) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		searchText = "%"+searchText+"%";
		offers = offersRepository.searchOthersByDescriptionAndUser(pageable, searchText, user);
		return offers;
	}

	public User buyOffer(Long id,User user) {
		Offer offer = offersRepository.findById(id).get();
		if(offer.getUser().getId() == (user.getId())) //comprobamos que no el comprador y el vendedor no son los mismos
			return null;
		if(offer.getBuyer() != null)//Comprobamos que no se ha comprado
			return null;
		if(offer.getPrice()>user.getSaldo()) //comprobamos que tiene saldo
			return null;
		user.setSaldo(user.getSaldo()-offer.getPrice());
		offer.setBuyer(user);
		
		offersRepository.save(offer);
		usersRepository.save(user);
		return user;
		
	}

	public List<Offer> getOffersBoughtBySearch(User user, String searchText) {
		List<Offer> offers = new ArrayList<Offer>();
		searchText = "%"+searchText+"%";
		offers = offersRepository.searchBoughtByDescriptionAndUser(searchText, user);
		return offers;
	}
	
	public List<Offer> getOffersBought(User user) {
		List<Offer> offers = new ArrayList<Offer>();
		offersRepository.findBoughtOffers(user).forEach(offers::add);
		return offers;
	}
}
