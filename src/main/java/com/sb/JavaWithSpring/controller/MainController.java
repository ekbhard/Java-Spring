package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Message;
import com.sb.JavaWithSpring.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String add(@RequestParam String text,@RequestParam String tag,Map<String,Object> model){
        Message message = new Message(text, tag);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages",messages);
        return "main";
    }

    @PostMapping("filer")
    public String filter(@RequestParam String fiter, Map<String,Object> model){
        List<Message> messages = messageRepo.findByTag(fiter);
        model.put("messages" , messages);
        return "main";
    }
}
