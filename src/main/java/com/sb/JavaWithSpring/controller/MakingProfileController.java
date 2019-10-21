package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.domain.UserProfile;
import com.sb.JavaWithSpring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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

    //TODO походу дата как то странно делется в spring data
    //TODO все еще непонятен вопрос в id...я пока вытаскиваю с текущего юзера через юзернэйм
    @PostMapping("/making-profile")
    public String addCity(@RequestParam String city,
                          @RequestParam String profile,
                          @RequestParam Date birthdayDate,
                          @RequestParam String achivments,
                          @RequestParam String hobbies,
                          Map<String,Object> model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepo.getUserByUsername(username);
        UserProfile userProfile = new UserProfile();
        userProfile.setProfile(profile);
        userProfile.setBirthdayDate(birthdayDate);
        userProfile.setAchivments(achivments);
        userProfile.setHobbies(hobbies);
        userProfile.setId(user.getId());
        user.setCity(city);
        userRepo.save(user);
        return "making-profile";
    }
}
