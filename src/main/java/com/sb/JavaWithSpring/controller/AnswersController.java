package com.sb.JavaWithSpring.controller;

import com.sb.JavaWithSpring.domain.Answer;
import com.sb.JavaWithSpring.repos.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Controller
public class AnswersController {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswersController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping("answers/{id}")
    public String displayAnswersByUser(@PathVariable String id, Map<String,Object> model) {
        List<Answer> answers = answerRepository.findAnswerByUser_IdOrderByCreatedDateDesc(Long.parseLong(id));
        model.put("answers", answers);
        return "answers";
    }

    @GetMapping("answers/useful/{id}")
    public String displayUsefulAnswersByUser(@PathVariable String id, Map<String,Object> model) {
        List<Answer> answers = answerRepository.findAnswerByUser_IdAndUsefulOrderByCreatedDateDesc(Long.parseLong(id), true);
        model.put("answers", answers);
        return "answers";
    }
}
