package com.uniovi.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.UsersService;

@Controller
public class HomeController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private HttpSession httpSession;
	
	@RequestMapping("/")
	public String index(Model model) {
		
		return "index";
	}
	
	
}
