package org.example.websocket.service;

import org.example.websocket.model.RecordsOfQNA;
import org.example.websocket.repository.QNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class QnaService {
    @Autowired
    private QNARepository qnaRepository;

    public void saveQna(RecordsOfQNA recordsOfQNA) {
        qnaRepository.save(recordsOfQNA);
    }
}
