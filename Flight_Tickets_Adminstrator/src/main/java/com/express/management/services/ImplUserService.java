package com.express.management.services;

import java.util.List;
import java.util.Set;

import com.express.management.entity.User;
import com.express.management.entity.User.UserType;
import com.express.management.exception.ResourceNotFoundException;

public interface ImplUserService {

	
	public List<User> createUser(List<User> users);
	public Set<User> viewAllUsers();
	public String removeUser(long userid) throws ResourceNotFoundException;
	public List<User> updateUser(List<User> user) throws ResourceNotFoundException;
	public User viewUser(long Userid) throws ResourceNotFoundException;
	public User findUserByUserNamePasswordUserType(String username, String password, UserType usertype) throws ResourceNotFoundException;
}
