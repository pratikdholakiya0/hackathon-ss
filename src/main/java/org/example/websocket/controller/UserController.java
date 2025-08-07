package org.example.websocket.controller;

import org.example.websocket.model.User;
import org.example.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public User getUser(String username){
        return userService.getUser(username);
    }

    @GetMapping("/response")
    public String response(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUser(username);

        return user.getFeedback();
    }
}
