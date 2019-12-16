package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Events;
import com.sb.JavaWithSpring.domain.Topic;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.repos.EventRepository;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EventsController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public EventsController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }


    @GetMapping("events")
    public String allevents(Map<String,Object> model){
        List<Events> events = eventRepository.findAll();
        model.put("events",events);
        User user = userRepository.findByUsername("Simon");
        if (events.size()==0){
            model.put("message","Ни одного события еще не было сделано");
            return "events";
        }
        return "events";
    }

    //-----------filters------------------------//

    @PostMapping("filterUser")
    public String filterUser(@RequestParam("userName") String userName,Map<String,Object> model){
        User user = null;
        try {
            user = userRepository.findByUsername(userName);
        } catch (Exception e) {
            model.put("message","Пользователя с таким именем не существует");
        }
        assert user != null;
        List<Events> events = user.getEvents();
        model.put("events",events);
        if (events.size()==0){
            model.put("message","Данный человек никуда не идет");
            return "events";
        }
        return "events";
    }

    @PostMapping("filterDate")
    public String filterDate(@RequestParam("date") String date,Map<String,Object> model){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");
        Date dateForFiltre = null;
        try {
            dateForFiltre = sdf.parse(date);
        } catch (ParseException e) {
            model.put("message","Формат даты нечитаем");
        }

        List<Events> events = eventRepository.findAll();
        List<Events> eventsToDelete = new ArrayList<>();
        for (Events event : events){
            assert dateForFiltre != null;
            if (event.getDateStart().before(dateForFiltre)){
                eventsToDelete.add(event);
            }
        }
        events.removeAll(eventsToDelete);
        model.put("events",events);
        if (events.size()==0){
            model.put("message","Ивенты после этой даты еще не запланированы");
            return "events";
        }
        return "events";
    }

    @PostMapping("filterEvent")
    public String filterEvent(@RequestParam("eventName") String eventName,Map<String,Object> model){
        List<Events> events = eventRepository.findEventsByTitle(eventName);
        model.put("events",events);
        if (events.size()==0){
            model.put("message","Ивентов с таким именем нет");
            return "events";
        }
        return "events";
    }
}
