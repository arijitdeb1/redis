package com.arijit.redis.redisservice.repository;

import com.arijit.redis.redisservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {


    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public void save(User user) {
        redisTemplate.opsForHash().put("USER",user.getId(),user);
    }

    public Map<Object, Object> findAll() {
        System.out.println("Calling DB.......");
        return redisTemplate.opsForHash().entries("USER");
    }

    public User findById(String id) {
        System.out.println("Calling database....");
        return (User)redisTemplate.opsForHash().get("USER",id);
    }

    public void update(User user) {
        save(user);
    }

    public void delete(String id) {
        redisTemplate.opsForHash().delete("USER", id);
    }
}
