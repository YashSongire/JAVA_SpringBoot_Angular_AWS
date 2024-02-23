package com.express.management.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.express.management.entity.User;
import com.express.management.exception.ResourceNotFoundException;
import com.express.management.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServices implements ImplUserService{

	@Autowired
	private UserRepository userRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServices.class);  
	
	// Creation of User
	@Override
	public List<User> createUser(List<User> users) {
		// TODO Auto-generated method stub
		LOG.info("Service - Create User");
		List<User> usersdata = userRepo.saveAll(users);
		return usersdata;
	}
	
	// All Users
	@Override
	public Set<User> viewAllUsers() {
		// TODO Auto-generated method stub
		LOG.info("Service - All UserDetails");
		Set<User> allusers = new HashSet<User>();
		userRepo.findAll().forEach(user1 -> allusers.add(user1));
		return allusers;
	}

	// Deletion of Existing User 
	@Override
	public String removeUser(long userid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service - Delete UserDetails");
		Optional<User> userdata = userRepo.findById(userid);
		if(userdata.isPresent()) {
			userRepo.deleteById(userid);
			return "User having " +userid+ " Deleted ";
		}
		else 
			throw new ResourceNotFoundException("No User Found with ID " +userid);
	}

	// Update Users 
	@Override
	public List<User> updateUser(List<User> user) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service - Update UserDetails");
		for(User userupdate : user) {
			Optional<User> existingUser = userRepo.findById(userupdate.getUserid());
			if(existingUser.isPresent()) {
				User updateduser = existingUser.get();
				updateduser.setUsername(userupdate.getUsername());
				updateduser.setUserpassword(userupdate.getUserpassword());
				updateduser.setUserphone(userupdate.getUserphone());
				updateduser.setUsertype(userupdate.getUsertype());
				updateduser.setUseremail(userupdate.getUseremail());
				userRepo.save(updateduser);
			}
			else
				throw new ResourceNotFoundException("Wrong User Id");
		}
		return user;
	}

	// Find User By ID
	@Override
	public User viewUser(long Userid) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		LOG.info("Service - Find UserDetails by ID");
		Optional<User> userdata = userRepo.findById(Userid);
		if(userdata.isPresent()) {
			return userdata.get();
		}
		else 
			throw new ResourceNotFoundException("No User Asscociated with ID" +Userid);
	}

}
