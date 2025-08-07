package org.example.websocket.controller;

import org.example.websocket.model.RecordsOfQNA;
import org.example.websocket.model.User;
import org.example.websocket.repository.QNARepository;
import org.example.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/answer")
public class AnswerController {
    @Autowired
    private QNARepository qnaRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public void saveAnswers(@RequestBody List<String> answers) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        User user = userService.getUser(username);
        user.getRecordsOfQNA().getAnswer().addAll(answers);
        userService.saveUser(user);
    }
}
