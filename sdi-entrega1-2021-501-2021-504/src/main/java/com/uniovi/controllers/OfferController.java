package com.uniovi.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;

@Controller
public class OfferController {

	@Autowired
	OffersService offersService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired 
	SecurityService securityService;
	
	@RequestMapping("/offer/add")
	public String getOffer(Model model) {
		return "offer/add";
	}
	
	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Offer offer) {
		//validar
		//if (result.hasErrors()) {
		//	model.addAttribute("usersList", usersService.getUsers());
			//return "/mark/add";
		//}
		offer.setDate(LocalDate.now());
		User user = usersService.getUserByEmail(securityService.findLoggedInEmail());
		offer.setUser(user);
		offersService.addOffer(offer);
		
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/list")
	public String getListado(Model model, Principal principal, @RequestParam(value = "", required=false) String searchText) {
		User user = usersService.getUserByEmail(securityService.findLoggedInEmail());
		List<Offer> offers = new ArrayList<Offer>();
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.getMyOffersBySearch(user, searchText);
		}
		else {
			offers = offersService.getMyOffers(user);
		}
		model.addAttribute("offerList", offers);
		model.addAttribute("deletesOffer", new ArrayList<Offer>());
		return "offer/list";
	}
	
	@RequestMapping("/offer/buyList")
	public String getListadoCompra(Model model, Principal principal, @RequestParam(value = "", required=false) String searchText) {
		User user = usersService.getUserByEmail(securityService.findLoggedInEmail());
		List<Offer> offers = new ArrayList<Offer>();
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.getOtherOffersBySearch(user, searchText);
		}
		else {
			offers = offersService.getOtherOffers(user);
		}
		model.addAttribute("offerList", offers);
		model.addAttribute("deletesOffer", new ArrayList<Offer>());
		return "offer/list";
	}
	
	@RequestMapping("/offer/boughtList")
	public String getListadoCompradas(Model model, Principal principal, @RequestParam(value = "", required=false) String searchText) {
		User user = usersService.getUserByEmail(securityService.findLoggedInEmail());
		List<Offer> offers = new ArrayList<Offer>();
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.getOffersBoughtBySearch(user, searchText);
		}
		else {
			offers = offersService.getOffersBought(user);
		}
		model.addAttribute("offerList", offers);
		model.addAttribute("deletesOffer", new ArrayList<Offer>());
		return "offer/list";
	}
	
	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/buy/{id}")
	public String buyOffer(@PathVariable Long id) {
		offersService.buyOffer(id);
		return "redirect:/offer/list";
	}
}
