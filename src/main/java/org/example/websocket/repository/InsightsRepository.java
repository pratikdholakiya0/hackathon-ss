package org.example.websocket.repository;

import org.example.websocket.model.UserInsights;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InsightsRepository extends MongoRepository<UserInsights, String> {
}
