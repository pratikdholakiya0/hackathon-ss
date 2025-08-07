package org.example.websocket.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInsights {
    private String role;
    private String experience;
    private String skills;
    private List<String> interviewType = new ArrayList<>();
    private String industry;
    private String jobDescription;
}
