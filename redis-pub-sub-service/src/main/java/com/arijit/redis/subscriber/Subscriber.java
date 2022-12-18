package com.arijit.redis.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;



public class Subscriber implements MessageListener {
    Logger logger= LoggerFactory.getLogger(Subscriber.class);
    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("Consume event {}", message);
    }
}
