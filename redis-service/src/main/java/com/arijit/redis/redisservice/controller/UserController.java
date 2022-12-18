package com.arijit.redis.redisservice.controller;

import com.arijit.redis.redisservice.model.User;
import com.arijit.redis.redisservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/add/{id}/{name}")
    @CachePut(key = "#id", value = "USER")
    public User add(@PathVariable("id") final String id, @PathVariable("name") final String name){
        userRepository.save(new User(id,name,20000L));
        return userRepository.findById(id);
    }

    @GetMapping("/update/{id}/{name}")
    public User update(@PathVariable("id") final String id, @PathVariable("name") final String name){
        userRepository.update(new User(id,name,10000L));
        return userRepository.findById(id);
    }

    @GetMapping("/all")
    public Map<Object, Object> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    @CacheEvict(key = "#id",value = "USER")
    public Map<Object, Object> delete(@PathVariable("id") String id){
        userRepository.delete(id);
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id",value = "USER")
    public User findById(@PathVariable("id") String id){
        return userRepository.findById(id);
    }
}
