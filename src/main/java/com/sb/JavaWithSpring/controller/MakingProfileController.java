package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MakingProfileController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/making-profile")
    public String main(Map<String,Object> model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepo.getUserByUsername(username);
        model.put("name", user.getId());
        model.put("id", user.getUsername());
        return "making-profile";
    }

    @PostMapping("/making-profile")
    public String addCity(@RequestParam String city, Map<String,Object> model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepo.getUserByUsername(username);
        user.setCity(city);
        userRepo.save(user);
        return "making-profile";
    }
}
