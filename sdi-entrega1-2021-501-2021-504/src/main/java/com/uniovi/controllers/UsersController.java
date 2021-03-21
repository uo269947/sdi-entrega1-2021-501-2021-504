package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UsersController {
	private Logger log = Logger.getGlobal();
	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SignUpFormValidator signUpFormValidator;
	

	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		model.addAttribute("usersList", usersService.getNormalUsers());
		model.addAttribute("deletesUser", new ArrayList<User>());
		log.log(Level.INFO, "El admin ha accedido a la lista de usuarios ");
		return "user/list";
	}



	@RequestMapping(value="/user/delete", method = RequestMethod.POST)
	public String delete(ServletWebRequest request) {
		String usuariosBorrados="[";
		if(request.getParameterValues("idChecked") != null){
	        for(String idCheckedStr : request.getParameterValues("idChecked")){
	        	usersService.deleteUser(Long.valueOf(idCheckedStr));
	        	usuariosBorrados+=idCheckedStr+" - ";
	            } 
	    }
		usuariosBorrados+="]";
		log.log(Level.INFO, "El admin ha eliminado los usuarios con ids: "+usuariosBorrados);
		return "redirect:/user/list";
	}

	

	

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		user.setSaldo(100.0);
		signUpFormValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		log.log(Level.INFO, "Se ha registrado el usuario: "+user);
		return "redirect:home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	

	
	

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		
		User activeUser = usersService.getUserByEmail(email);
		log.log(Level.INFO, "Está autenticado el usuario: "+activeUser);
		httpSession.setAttribute("authUsser", activeUser);
		return "home";
		
	}
	
	
}