package io.api.base.components;

import io.api.base.domain.Ocupacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisSender {




    @Autowired
    private RedisTemplate<String, Ocupacion> redisTemplateEmail;

    @Autowired
    private ChannelTopic topic;



    public void sendDataToRedisQueue(Ocupacion input) {
        redisTemplateEmail.convertAndSend(topic.getTopic(), input);
        log.info("Data - " + input + " sent through Redis Topic - " + topic.getTopic());
    }
}
