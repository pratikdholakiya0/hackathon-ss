package org.example.websocket.repository;

import org.example.websocket.model.RecordsOfQNA;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QNARepository extends MongoRepository<RecordsOfQNA, String> {
    RecordsOfQNA getById(String id);
}
