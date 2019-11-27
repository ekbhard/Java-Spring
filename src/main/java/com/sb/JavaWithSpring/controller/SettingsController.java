package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.ProfileImage;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.domain.UserProfile;
import com.sb.JavaWithSpring.repos.ImageRepository;
import com.sb.JavaWithSpring.repos.UserProfileRepo;
import com.sb.JavaWithSpring.repos.UserRepository;
import com.sb.JavaWithSpring.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Random;

@Controller
public class SettingsController {

    public final String USERS_IMAGES_PATH = "src/main/resources/images/";
    public final String[] imgNames = {"avatar1612-1.jpg",
            "avatar1613.jpg","avatar1614.jpg","avatar1644.jpg","avatar1646.jpg","avatar1649.jpg",
            "avatar1693.jpg","avatar1695.jpg","avatar1697.jpg","avatar1699.jpg","avatar1699-1.jpg",
            "avatar1700.jpg","avatar1700-1.jpg","avatar1702.jpg","avatar1705.jpg"};

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserProfileRepo userProfileRepo;

    @GetMapping("/settings")
    public String main(Map<String,Object> model){
        return "settings";
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
        User user = userRepository.getUserByUsername(username);
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        //блок с закидыванием картинки
        try {
            ProfileImage profileImage = new ProfileImage();
            profileImage.setUser(user);
            String randName = imgNames[new Random().nextInt(imgNames.length)];
            String imagePath = USERS_IMAGES_PATH + randName;
            profileImage.setProfilePicture(ImageService.getBytesFromFile(imagePath));
            imageRepository.save(profileImage);
        } catch (IOException e) {
            System.out.println("Картинка не загрузилась");
        }

        try {
            userProfile.setDateBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthdayDate));
        }catch (ParseException e){}
        userProfile.setProfile(profile);
        userProfile.setCareer(career);
        userProfile.setAchievements(achievements);
        userProfile.setHobbies(hobbies);
        userProfile.setCity(city);
        userProfileRepo.save(userProfile);
        return "/settings";
    }
}
