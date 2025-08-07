package org.example.websocket.repository;

import org.example.websocket.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);

    User getUsersByUsername(String username);
}
