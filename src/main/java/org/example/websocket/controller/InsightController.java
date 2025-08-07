package org.example.websocket.controller;

import org.example.websocket.model.User;
import org.example.websocket.model.UserInsights;
import org.example.websocket.repository.InsightsRepository;
import org.example.websocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/insights")
public class InsightController {
    @Autowired
    private InsightsRepository insightsRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveInsights(@RequestBody UserInsights userInsights) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            User user = userRepository.getUsersByUsername(username);
            user.setUserInsights(userInsights);
            userRepository.save(user);

            return ResponseEntity.ok("Insights saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error saving insights: " + e.getMessage());
        }
    }
}
