package com.express.management.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.express.management.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	
//	public Optional<List<Schedule>> findBydateAndTimeOfDeparture(Date dateAndTimeOfDeparture);
	
    @Query("select a from Schedule a where a.dateAndTimeOfDeparture = :creationDateTime")
    Optional<List<Schedule>> findAllWithCreationDateTime(
      @Param("creationDateTime") LocalDateTime creationDateTime);
    
    @Query("SELECT s FROM Schedule s WHERE TO_CHAR(s.dateAndTimeOfDeparture, 'YYYY-MM-DD') = TO_CHAR(:date, 'YYYY-MM-DD')")
    Optional<List<Schedule>> findByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date);
}
