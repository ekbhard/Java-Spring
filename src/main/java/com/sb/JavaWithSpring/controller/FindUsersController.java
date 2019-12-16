package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.ProfileImage;
import com.sb.JavaWithSpring.domain.Topic;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.domain.UserProfile;
import com.sb.JavaWithSpring.repos.ImageRepository;
import com.sb.JavaWithSpring.repos.UserProfileRepo;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class FindUsersController {
    private final UserRepository userRepository;
    private final UserProfileRepo userProfileRepo;
    private final ImageRepository imageRepository;


    @Autowired
    public FindUsersController(UserRepository userRepository, UserProfileRepo userProfileRepo,ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.userProfileRepo = userProfileRepo;
        this.imageRepository = imageRepository;
    }

    @GetMapping("profiles")
    public String users(Map<String,Object> model){
        List<User> users = userRepository.findAll();
        model.put("users",users);
        return "profiles";
    }

    @GetMapping("profiles/{userid}")
    public String profile(Map<String,Object> model,
                                @PathVariable Long userid) {
        User user = userRepository.getUserById(userid);
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

    //---------------------filters---------------//

    @PostMapping("filterUserName")
    public String filterUserName(@RequestParam("name") String name, Map<String,Object> model){
        User user =userRepository.getUserByUsername(name);
        model.put("users",user);
        if (user==null){
            model.put("message","Человека с таким именем еще нет в штате");
            return "profiles";
        }
        return "profiles";
    }
}
