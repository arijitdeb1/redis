package com.arijit.redis.publisher;

import com.arijit.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
public class Publisher {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ChannelTopic topic;

    @PostMapping("/publish")
    public String publish(@RequestBody User user) {
        System.out.println("********"+user.toString());
        redisTemplate.convertAndSend(topic.getTopic(), user.toString());
        return "event published...";
    }
}
