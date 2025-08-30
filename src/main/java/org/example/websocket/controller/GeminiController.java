package org.example.websocket.controller;

import org.checkerframework.checker.units.qual.A;
import org.example.websocket.model.PromptRequest;
import org.example.websocket.model.RecordsOfQNA;
import org.example.websocket.model.User;
import org.example.websocket.model.UserInsights;
import org.example.websocket.repository.QNARepository;
import org.example.websocket.service.GeminiService;
import org.example.websocket.service.QnaService;
import org.example.websocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/gemini")
public class GeminiController {
    @Autowired
    private QnaService  qnaService;

    @Autowired
    private QNARepository qnaRepository;

    @Autowired
    private GeminiService geminiService;
    @Autowired
    private UserService userService;

    @GetMapping("/answer")
    public String generateContent(@RequestBody PromptRequest prompt) {
        return geminiService.generateText(prompt.getPrompt());
    }

    @PostMapping("/starterQuestion")
    public String[] generateStarterQuestion(@RequestBody UserInsights userInsights) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUser(username);

        String promptAdmin = "The list should include a mix of the following types of questions Behavioral questions Situational questions Please provide only the numbered list of questions without any additional commentary answers or explanations in sequence of general interview also include basic questionThe list should include a mix of the following types of questions Behavioral questions Situational questions Please provide only the numbered list of questions without any additional commentary answers or explanations in sequence of general interview also include basic question requriding " + user.getUserInsights().toString() + "generate 12 most required question";

        String questions =  geminiService.generateText(promptAdmin);

        RecordsOfQNA qna = new RecordsOfQNA();
        String q[] = questions.split("\n");

        for (String qn : q) {
            qna.getQuestion().add(qn);
        }

        user.setRecordsOfQNA(qna);
        userService.saveUser(user);
        return q;
    }

    @GetMapping("/verifyAnswer")
    public void verifyAnswer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.getUser(username);
        RecordsOfQNA question = user.getRecordsOfQNA();

    }
}
