package com.food.app.controller;

import com.food.app.dto.Login;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.food.app.dto.UserDto;
import com.food.app.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/user")
@NoArgsConstructor
@AllArgsConstructor
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);

	UserService userService;


	//testing
	@GetMapping("/check")
	public String testString() {
		return " its working!";
	}

	// user registration
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		logger.info("UserController class with createUser method");
		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
	}

	//user login
	@GetMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody Login login){
		logger.info("UserController class userLogin method with the email {} :", login.getEmail());
		return new ResponseEntity<>(userService.userLogin(login), HttpStatus.FOUND);
	}

	//user deletion
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email){
		logger.info("UserController class deleteUser method with the email {} :", email);
		return new ResponseEntity<>(userService.deleteUser(email), HttpStatus.FOUND);
	}
}
