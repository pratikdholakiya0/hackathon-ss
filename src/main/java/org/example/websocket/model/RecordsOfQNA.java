package org.example.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordsOfQNA {
    @Id
    private String id;
    private List<String> question = new ArrayList<>();
    private List<String> answer = new ArrayList<>();

    private String feedback;
}
