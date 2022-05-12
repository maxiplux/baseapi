package io.api.base.controllers;

import io.api.base.components.RedisSender;
import io.api.base.domain.Ocupacion;
import io.api.base.repository.OcupacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/redis")
public class RedisController {

    @Autowired
    private RedisSender sender;

    @Autowired
    private OcupacionRepository ocupacionRepository;


    @GetMapping(value = "simple")
    public String sendDataToRedisQueue() {
        List<Ocupacion> ocupacionList = (List<Ocupacion>) ocupacionRepository.findAll();

        sender.sendDataToRedisQueue(ocupacionList.get(0));
        return "successfully sent";
    }
}
