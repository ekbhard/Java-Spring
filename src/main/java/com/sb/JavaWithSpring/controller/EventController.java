package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Events;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.repos.EventRepository;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class EventController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("event/{id}")
    public String displayTopic(@PathVariable String id, Map<String,Object> model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Long idUser = userRepository.getUserByUsername(username).getId();
        Events event = eventRepository.findEventsById(Long.valueOf(id));
        model.put("event", event);
        model.put("usersJoin", event.getUsers());
        return "event";
    }

    @PostMapping("event/{id}")
    public View addUser(@PathVariable String id, Map<String,Object> model, HttpServletRequest request ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.getUserByUsername(username);
        Events event = eventRepository.findEventsById(Long.valueOf(id));
        if (!event.getUsers().contains(user)) {
            event.setUsers(user);
            eventRepository.save(event);
        }
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/event/" + id);
    }

}
