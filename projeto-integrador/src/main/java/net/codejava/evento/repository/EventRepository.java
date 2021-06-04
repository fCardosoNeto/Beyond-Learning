package net.codejava.evento.repository;

import java.time.LocalDateTime;
import java.util.List;

import net.codejava.evento.controller.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	@Query("from Event e where not(e.end < :from or e.start > :to)")
	public List<Event> findBetween(@Param("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);
}