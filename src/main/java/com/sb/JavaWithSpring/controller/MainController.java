package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Message;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;
    //модель приветствия
    @GetMapping("/")
    public String greeting( Map<String , Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String,Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,Map<String,Object> model){
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages",messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object> model){
        List<Message> messages = messageRepo.findByTag(filter);
        model.put("messages" , messages);
        return "main";
    }
}
