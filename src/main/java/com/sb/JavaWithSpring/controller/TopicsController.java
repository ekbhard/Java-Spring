package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Events;
import com.sb.JavaWithSpring.domain.Topic;
import com.sb.JavaWithSpring.domain.User;
import com.sb.JavaWithSpring.repos.AnswerRepository;
import com.sb.JavaWithSpring.repos.TopicRepository;
import com.sb.JavaWithSpring.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class TopicsController {

    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;

    @Autowired
    public TopicsController(TopicRepository topicRepository, AnswerRepository answerRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("topics")
    public String displayAllTopics(Map<String,Object> model) {
        List<Topic> topics = topicRepository.findAll(new Sort(Sort.Direction.DESC, "createdDate"));
        String header = setHeader("all");
        model.put("topics", topics);
        model.put("header", header);
        model.put("answerRepository", answerRepository);
        return "topics";
    }

    @GetMapping("topics/{category}")
    public String displayTopicsByCategory(@PathVariable String category, Map<String,Object> model) {
        List<Topic> topics = topicRepository.findTopicsByCategoryOrderByCreatedDateDesc(category);
        String header = setHeader(category);
        model.put("topics", topics);
        model.put("header", header);
        model.put("answerRepository", answerRepository);
        return "topics";
    }

    @GetMapping("topics/user/{id}")
    public String displayTopicsByUser(@PathVariable String id, Map<String,Object> model) {
        List<Topic> topics = topicRepository.findTopicsByUser_IdOrderByCreatedDateDesc(Long.valueOf(id));
        String header = setHeader("user");
        model.put("topics", topics);
        model.put("header", header);
        model.put("answerRepository", answerRepository);
        return "topics";
    }

    private String setHeader(String category) {
        switch (category) {
            case "se":
                return "Java Standard Edition";
            case "ee":
                return "Java Enterprise Edition";
            case "jpa":
                return "Java Persistence API and Hibernate";
            case "spring":
                return "Spring Framework";
            case "web":
                return "HTML/CSS/JavaScript";
            case "all":
                return "All topics";
            default:
                return "User's topics";
        }
    }

    //-----------filters------------------------//

    @PostMapping("filterUserTopic")
    public String filterUserTopic(@RequestParam("userName") String userName, Map<String,Object> model){
        User user = null;
        try {
            user = userRepository.findByUsername(userName);
        } catch (Exception ignore) {}
       if (user == null){
           model.put("message","Пользователя с таким именем не существует");
           return "topics";
       }
        List<Topic> topics = topicRepository.findTopicsByUser_IdOrderByCreatedDateDesc(user.getId());
        model.put("topics",topics);
        if (topics.size()==0){
            model.put("message","Данный человек еще не" +
                    " участвует ни в одном обсуждении");
            return "topics";
        }
        return "topics";
    }

    @PostMapping("filterCategory")
    public String filterCategory(@RequestParam("category") String category,Map<String,Object> model){
        List<Topic> topics = topicRepository.findTopicsByCategory(category);
        model.put("topics",topics);
        if (topics.size()==0){
            model.put("message","Топиков с такой категорией нет");
            return "topics";
        }
        return "topics";
    }

    @PostMapping("filterTitle")
    public String filterTitle(@RequestParam("title") String title,Map<String,Object> model){
        List<Topic> topics = topicRepository.findTopicsByTitle(title);
        model.put("topics",topics);
        if (topics.size()==0){
            model.put("message","Топиков с такой категорией нет");
            return "topics";
        }
        return "topics";
    }
}