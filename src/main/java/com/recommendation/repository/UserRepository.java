package com.recommendation.repository;

import com.recommendation.model.User;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    User findByPhone(Long phone);
}
