package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.ProfileImage;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.domain.UserProfile;
import com.sb.JavaWithSpring.repos.ImageRepository;
import com.sb.JavaWithSpring.repos.UserProfileRepo;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Map;

@Controller
public class ProfileController {



    private final UserRepository userRepository;
    private final UserProfileRepo userProfileRepo;
    private final ImageRepository imageRepository;


    @Autowired
    public ProfileController(UserRepository userRepository, UserProfileRepo userProfileRepo,ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.userProfileRepo = userProfileRepo;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/profile")
    public String profile(Map<String,Object> model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.getUserByUsername(username);

        if (user==null){
            System.out.println("Юзер мертв!");
        }else {
            System.out.println("Я жив! : " + user.getUsername());
        }
        ProfileImage profileImage = null;
        try {
            assert user != null;
            profileImage = imageRepository.getProfileImageByUser_Id(user.getId());
        } catch (Exception e) {
            model.put("message","Картики еще нет");
        }
        model.put("name", user.getUsername());
        model.put("id", user.getId());
        UserProfile up = userProfileRepo.getUserProfileByUser_Id(user.getId());
        model.put("city",up.getCity());
        model.put("dateBirthday",up.getDateBirthday().toString());
        model.put("achievements",up.getAchievements());
        model.put("career",up.getCareer());
        model.put("hobbies",up.getHobbies());
        model.put("profile",up.getProfile());
        model.put("imgPath",profileImage.getBase64Img());
        return "profile";
    }

}
