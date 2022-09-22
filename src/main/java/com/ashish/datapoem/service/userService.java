package com.ashish.datapoem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.datapoem.Entity.User;
import com.ashish.datapoem.repositories.UserRepository;
@Service
public class userService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void addmoneyTowallet(Integer id,Integer money) {
		User user=userRepository.findById(id).get();
		user.setWallet(user.getWallet()+money);
		userRepository.save(user);
		
	}
	
	public User authenticate(String email) {
		return userRepository.findByEmail(email).get();
	}
	
}
