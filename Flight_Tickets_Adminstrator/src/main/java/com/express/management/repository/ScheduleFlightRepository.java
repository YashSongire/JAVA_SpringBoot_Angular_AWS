package com.express.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.express.management.entity.ScheduledFlight;

@Repository
public interface ScheduleFlightRepository extends JpaRepository<ScheduledFlight, Long>{

}
