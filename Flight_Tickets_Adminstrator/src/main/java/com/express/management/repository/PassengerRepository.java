package com.express.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.express.management.entity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
