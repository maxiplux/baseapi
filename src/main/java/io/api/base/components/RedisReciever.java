package io.api.base.components;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;


public class RedisReciever implements MessageListener {



    @Override
    public void onMessage(Message message, byte[] pattern) {
        // TODO Auto-generated method stub

        //log.info("Received data - " + message.toString() + " from Topic - " + new String(pattern));
    }
}
