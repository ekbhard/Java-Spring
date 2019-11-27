package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Topic;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.repos.AnswerRepository;
import com.sb.JavaWithSpring.repos.TopicRepository;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Controller
public class CreateTopicController {
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public CreateTopicController(UserRepository userRepository, TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @GetMapping("/createTopic")
    public String main(Map<String,Object> model){
        return "createTopic";
    }

    @PostMapping("createTopic")
    public String addTask(@RequestParam("category") String category,
                          @RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("code") String code) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user = userRepository.getUserByUsername(username);

        Topic topic = new Topic();
        topic.setCategory(category);
        if (Objects.equals(code, ""))
            topic.setCode(null);
        else
            topic.setCode(code);

        topic.setContent(content);
        topic.setTitle(title);
        topic.setCreatedDate(LocalDateTime.now());
        topic.setUser(user);

        topicRepository.save(topic);
        return "redirect:/topics";
    }
}
