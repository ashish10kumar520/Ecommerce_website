package com.ashish.datapoem.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashish.datapoem.Entity.Category;
import com.ashish.datapoem.Entity.CustomerUserDetail;
import com.ashish.datapoem.Entity.Product;
import com.ashish.datapoem.Entity.User;
import com.ashish.datapoem.global.GlobalData;
import com.ashish.datapoem.repositories.UserRepository;
import com.ashish.datapoem.service.ProductService;
import com.ashish.datapoem.service.userService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
@Controller
@Slf4j
public class CartController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private userService userService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	public String cartGet(@AuthenticationPrincipal User user,Model model) {
		
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart",GlobalData.cart);
		model.addAttribute("balance",userService.getMoneyFromWallet(user.getId()));
		return "cart";
	}
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	@GetMapping("/checkout")
	public String checkout(@AuthenticationPrincipal User user,Model model) {
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("balance",userService.getMoneyFromWallet(user.getId()));
		return "checkout";
	}
	@GetMapping(value="/purchased")
	public String purchased(@AuthenticationPrincipal User user,Model model){
		Double total=GlobalData.cart.stream().mapToDouble(Product::getPrice).sum();
		Integer walletmoney=userService.getMoneyFromWallet(user.getId());
		Double result=(Integer)walletmoney-total;
		System.out.println(result);
		model.addAttribute("result",result);
		return "redirect:/cart";
	}
	@RequestMapping(value="/addedmoney",method = RequestMethod.POST)
	public String postCatAdd(@RequestParam("addedmoney")Integer money) {
		CustomerUserDetail principal = (CustomerUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal.getId()!=null) {
			userService.addbalance(principal.getId(), money);
		}
		return "redirect:/cart";
	}
	
}
