package com.ashish.datapoem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ashish.datapoem.Entity.Role;
import com.ashish.datapoem.Entity.User;
import com.ashish.datapoem.global.GlobalData;
import com.ashish.datapoem.repositories.RoleRepository;
import com.ashish.datapoem.repositories.UserRepository;
import com.ashish.datapoem.service.userService;

import org.springframework.security.core.context.SecurityContext;
@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private userService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//@Autowired
	//private AuthenticationManager authenticationManager;
	
	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "login.html";
	}
	
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	@PostMapping("/login")
	public String loginPost(@ModelAttribute("loginrequest") User user,Model model) throws ServletException{
		System.out.print(user.getEmail());
		User StringUser=userService.authenticate(user.getEmail());
		
		Boolean isAdmin=StringUser.getRoles().contains(1);
		
		if(StringUser!=null) {
			if(isAdmin) {
				return "/adminHome";
			}
			return "/shop";
		}
		else {
			return "/login";
		}
		//if(user.getPassword().equals(passString)) {
			//UsernamePasswordAuthenticationToken authReq= new UsernamePasswordAuthenticationToken(email, password);
			//Authentication auth = authenticationManager.authenticate(authReq);
			//SecurityContext sc= SecurityContextHolder.getContext();
			//sc.setAuthentication(auth);
			//return "redirect:/";
		//}
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException{
		String password =user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles=new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		userRepository.save(user);
		request.login(user.getEmail(), password);
		return "redirect:/";
	}
}
