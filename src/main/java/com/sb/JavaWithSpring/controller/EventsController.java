package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Events;
import com.sb.JavaWithSpring.repos.EventRepository;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class EventsController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventsController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public String allevents(Map<String,Object> model){
        List<Events> events = eventRepository.findAll();
        model.put("events",events);
        return "events";
    }
}
