package com.uniovi.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String setMark( @ModelAttribute Offer offer) {
		//validar
		//if (result.hasErrors()) {
		//	model.addAttribute("usersList", usersService.getUsers());
			//return "/mark/add";
		//}
		offer.setDate(LocalDate.now());
		
	
		User user = usersService.getUserByEmail(securityService.findLoggedInEmail());
		offer.setUser(user);
		offersService.addOffer(offer);
		
		return "offer/add";
	}
}
