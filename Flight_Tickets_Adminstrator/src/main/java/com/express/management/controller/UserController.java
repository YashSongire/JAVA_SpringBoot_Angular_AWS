package com.express.management.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.express.management.entity.User;
import com.express.management.entity.User.UserType;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.services.ImplUserService;

import jakarta.validation.Valid;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private ImplUserService userservice;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	// Create User
	@PostMapping("/users")
	public ResponseEntity<List<User>> createUser(@Valid @RequestBody List<User> users){
		LOG.info("Controller - All Users");
		List<User> userdata = userservice.createUser(users);
		return new ResponseEntity<List<User>>(userdata,HttpStatus.OK);
	}
	
	// Get All Users
	@GetMapping("/users")
	public ResponseEntity<Set<User>> viewAllUsers(){
		LOG.info("Controller - All Users");
		Set<User> allusers = userservice.viewAllUsers();
		return new ResponseEntity<Set<User>>(allusers,HttpStatus.OK);
	};
	
	// Deletion of User
	@DeleteMapping("/users/{userid}")
	public ResponseEntity<String> removeUser(@Valid @PathVariable long userid) throws ResourceNotFoundException{
		LOG.info("Controller - Delete user : " +userid);
		String message = userservice.removeUser(userid);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	// Update List of Users
	@PutMapping("/users")
	public ResponseEntity<List<User>> updateUser(@Valid @RequestBody List<User> user) throws ResourceNotFoundException{
		LOG.info("Controller - Update UserDetails");
		List<User> userdata = userservice.updateUser(user);
		return new ResponseEntity<List<User>>(userdata,HttpStatus.OK);
	}
	
	// Get User By ID 
	@GetMapping("/users/{Userid}")
	public ResponseEntity<User> viewUser(@Valid @PathVariable long Userid) throws ResourceNotFoundException{
		LOG.info("Controller - User Detais ID : " +Userid);
		User user = userservice.viewUser(Userid);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	// Get User By UserName
	@GetMapping("/users/userdata")
	public ResponseEntity<User> viewUserbyUsernamePasswordType(@Valid @RequestParam String username,
            @RequestParam String password,
            @RequestParam UserType type) throws ResourceNotFoundException{
		LOG.info("Controller - User Detais ID : " +username+type);
		User user = userservice.findUserByUserNamePasswordUserType(username, password, type);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}