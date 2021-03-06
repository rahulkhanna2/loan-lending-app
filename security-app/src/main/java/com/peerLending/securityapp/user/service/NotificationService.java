package com.peerLending.securityapp.user.service;

import com.google.gson.Gson;
import com.peerLending.securityapp.user.dto.UserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;
    private final static Gson gson = new Gson();

    @Autowired
    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(UserDTO userDto){
        userDto.setPassword("************");
        rabbitTemplate.convertAndSend("userRegisteredTopic", "user.registered", gson.toJson(userDto));
    }

}
