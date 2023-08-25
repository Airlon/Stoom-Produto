package br.com.stoom.store.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String nameQueue, Object message) {
        log.info("Sending message to queue {}: {}", nameQueue, message);
        this.rabbitTemplate.convertAndSend(nameQueue, message);
    }
}
