package com.express.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.express.management.entity.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long>{

}
