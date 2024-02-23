package com.express.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.express.management.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
}
