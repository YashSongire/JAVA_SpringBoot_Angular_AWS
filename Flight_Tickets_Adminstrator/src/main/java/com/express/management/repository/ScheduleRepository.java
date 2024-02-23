package com.express.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.express.management.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	
}
