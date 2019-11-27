package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String userList(Map<String,Object> model){
        List<User> users = userRepository.findAll();
        model.put("users",users);
        return "userList";
    }
}
