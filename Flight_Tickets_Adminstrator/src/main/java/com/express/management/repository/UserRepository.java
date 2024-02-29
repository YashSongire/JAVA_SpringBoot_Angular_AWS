package com.express.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.express.management.entity.User;
import com.express.management.entity.User.UserType;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u " +
	           "WHERE u.username = :Username " +
	           "AND u.userpassword = :Password " +
	           "AND u.usertype = :Type")
	Optional<User> findByUserNamePasswordTYpe(
			@Param("Username") String username, 
			@Param("Password")String password, 
			@Param("Type") UserType usertype);
}
