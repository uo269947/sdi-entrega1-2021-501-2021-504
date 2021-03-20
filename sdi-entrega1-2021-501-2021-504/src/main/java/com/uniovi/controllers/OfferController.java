package com.uniovi.controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	HttpSession httpSession;
	
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
	public String getListadoCompra(Model model, Pageable pageable, Principal principal, @RequestParam(value = "", required=false) String searchText) {
		User user = usersService.getUserByEmail(securityService.findLoggedInEmail());
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.getOtherOffersBySearch(pageable, user, searchText);
		}
		else {
			offers = offersService.getOtherOffers(pageable, user);
		}
		model.addAttribute("offerList", offers.getContent());
		//model.addAttribute("deletesOffer", new ArrayList<Offer>());
		return "offer/buyList";
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
		return "offer/boughtList";
	}
	
	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/list";
	}
	
	@RequestMapping("/offer/buy/{id}")
	public String buyOffer(Model model, Pageable pageable, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		User user = offersService.buyOffer(id,activeUser);
		if( user== null)
			return "home";
		
		httpSession.setAttribute("authUsser", user);
		offers = offersService.getOtherOffers(pageable, user);	
		model.addAttribute("offerList", offers.getContent());
		return "redirect:/offer/buyList";
	}
}
