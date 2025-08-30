package org.example.websocket.controller;

import org.apache.http.HttpEntity;
import org.example.websocket.model.RecordsOfQNA;
import org.example.websocket.model.User;
import org.example.websocket.repository.QNARepository;
import org.example.websocket.service.GeminiService;
import org.example.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
<<<<<<< HEAD

=======
>>>>>>> d02ef0e6f8b9a61ac4e70bb96735637b9576ce20

@RestController
@RequestMapping("/public/answer")
public class AnswerController {
    @Autowired
    private QNARepository qnaRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

<<<<<<< HEAD
    @Autowired
    private GeminiService geminiService;

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

=======
>>>>>>> d02ef0e6f8b9a61ac4e70bb96735637b9576ce20
    @PostMapping("/save")
    public void saveAnswers(@RequestBody List<String> answers) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        User user = userService.getUser(username);
        for (int i = 0; i < answers.size(); i++) {
            answers.set(i, (i+ 1) + " " + answers.get(i));
        }

        System.out.println(answers);
        user.getRecordsOfQNA().getAnswer().addAll(answers);
        userService.saveUser(user);

        RecordsOfQNA recordsOfQNA = user.getRecordsOfQNA();
        String resp =  "verify answer of every question with given answer via indexes and give feed back answers " + recordsOfQNA.getQuestion().toString() + " questions " + recordsOfQNA.getAnswer().toString();
        String geminiResponse = geminiService.generateText(resp);
        user.setFeedback(geminiResponse);

        userService.saveUser(user);
    }
}
