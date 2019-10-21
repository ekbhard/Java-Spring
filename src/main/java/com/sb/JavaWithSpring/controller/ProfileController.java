package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Message;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.domain.UserProfile;
import com.sb.JavaWithSpring.repos.MessageRepo;
import com.sb.JavaWithSpring.repos.UserProfileRepo;
import com.sb.JavaWithSpring.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class ProfileController {

    private final UserRepo userRepo;
    private final UserProfileRepo userProfileRepo;


    @Autowired
    public ProfileController(UserRepo userRepo, UserProfileRepo userProfileRepo) {
        this.userRepo = userRepo;
        this.userProfileRepo = userProfileRepo;
    }

    @GetMapping("/personalarea")
    public String main(Map<String,Object> model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepo.getUserByUsername(username);
        model.put("name", user.getId());
        model.put("id", user.getUsername());
        return "personalarea";
    }

    @PostMapping("profile")
    public String addProfile(@RequestParam("city") String city, @RequestParam("info") String info,
                        @RequestParam("birthday") Date birthday ,@RequestParam("city") Long userId) {
        UserProfile userProfile = new UserProfile();
        //имя конечно это круто , но если будут люди с одинаковыми именами , нужен сразу
        // id человека который вошел сейчас...
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepo.getUserByUsername(username);

        userProfile.setUser(user);
        userProfile.setCity(city);
        userProfile.setProfile(info);
        userProfile.setBirhdayDate(birthday);
        return "profile";
    }
}
