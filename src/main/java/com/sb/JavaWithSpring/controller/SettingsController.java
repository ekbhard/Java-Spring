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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class SettingsController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserProfileRepo userProfileRepo;

    @GetMapping("/settings")
    public String main(Map<String,Object> model){
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = ((UserDetails)principal).getUsername();
//        User user = userRepo.getUserByUsername(username);
//        model.put("name", user.getUsername());
//        model.put("id", user.getId());
        return "/settings";
    }

    @PostMapping("settings")
    public String addProfile(@RequestParam("city") String city,
                             @RequestParam("profile") String profile,
                             @RequestParam ("birthdayDate") String birthdayDate,
                             @RequestParam ("achievements") String achievements,
                             @RequestParam ("hobbies") String hobbies,
                             @RequestParam ("career") String career,
                             Map<String,Object> model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepo.getUserByUsername(username);
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        try {
            userProfile.setDateBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayDate));
        }catch (ParseException e){}
        userProfile.setProfile(profile);
        userProfile.setCareer(career);
        userProfile.setAchievements(achievements);
        userProfile.setHobbies(hobbies);
        userProfile.setCity(city);
        userProfileRepo.save(userProfile);
        return "settings";
    }
}
