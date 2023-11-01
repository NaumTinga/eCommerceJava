package com.example.demo.controllers;

import java.util.Optional;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			log.info("User Not Found. id " + id);
			return ResponseEntity.notFound().build();
		} else {
			log.info("User Found. id " + id);
			return ResponseEntity.ok(user.get());
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			log.info("UserController | findByUsername | User Not Found. username.: " + username);
			return ResponseEntity.notFound().build();
		} else {
			log.info("UserController | findByUsername | User Found. username.: " + username);
			return ResponseEntity.ok(user);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);

		// Check if the password is not null and if it matches the confirmPassword
		if (createUserRequest.getPassword() != null && createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
			// Check if the password length is at least 7 characters
			if (createUserRequest.getPassword().length() >= 7) {
				user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
				userRepository.save(user);
				log.info("User created successfully: username: " + user.getUsername());
				return ResponseEntity.ok(user);
			}
		}

		// If the conditions are not met, return a bad request response
		log.info("User not created, password should have more 7 or more characters");
		return ResponseEntity.badRequest().build();
	}


}
