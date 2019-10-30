package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.domain.UserProfile;
import com.sb.JavaWithSpring.repos.UserProfileRepo;
import com.sb.JavaWithSpring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class ProfileController {

    private final UserRepo userRepo;
    private final UserProfileRepo userProfileRepo;


    @Autowired
    public ProfileController(UserRepo userRepo, UserProfileRepo userProfileRepo) {
        this.userRepo = userRepo;
        this.userProfileRepo = userProfileRepo;
    }

    @GetMapping("/profile")
    public String profile(Map<String,Object> model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepo.getUserByUsername(username);
        if (user==null){
            System.out.println("Юзер мертв!");
        }else {
            System.out.println("Я жив! : " + user.getUsername());
        }
        model.put("name", user.getUsername());
        model.put("id", user.getId());
        UserProfile up = userProfileRepo.findCurrentUserProfile(user.getId());
        model.put("city",up.getCity());
        model.put("dateBirthday",up.getDateBirthday().toString());
        model.put("achievements",up.getAchievements());
        model.put("career",up.getCareer());
        model.put("hobbies",up.getHobbies());
        model.put("profile",up.getProfile());
        return "profile";
    }

}
