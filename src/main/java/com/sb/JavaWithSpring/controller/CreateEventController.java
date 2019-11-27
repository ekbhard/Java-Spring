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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CreateEventController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public CreateEventController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/createEvent")
    public String main(Map<String,Object> model){
        return "createEvent";
    }

    @PostMapping("createEvent")
    public View createEvent(Map<String,Object> model,
                            @RequestParam String place,
                            @RequestParam String title,
                            @RequestParam String text,
                            @RequestParam String dateStart,
                            @RequestParam String dateEnd,
                            HttpServletRequest request){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.getUserByUsername(username);
        List<User> users = new ArrayList<>();
        users.add(user);
        Events event = new Events();
        event.setText(text);
        event.setTitle(title);
        try {
            event.setDateEnd(new SimpleDateFormat("yyyy-MM-dd").parse(dateEnd));
        }catch (ParseException e){}

        try {
            event.setDateStart(new SimpleDateFormat("yyyy-MM-dd").parse(dateStart));
        }catch (ParseException e){}
        event.setPlace(place);
        event.setUsers(users);
        eventRepository.save(event);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/events");
    }

}
