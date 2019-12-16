package com.sb.JavaWithSpring.repos;

import com.sb.JavaWithSpring.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EventRepository extends JpaRepository<Events, Long> {

    Events getEventsById(long Id);
    Events findEventsById(Long Id);
    List<Events> findEventsByTitle(String name);


}
