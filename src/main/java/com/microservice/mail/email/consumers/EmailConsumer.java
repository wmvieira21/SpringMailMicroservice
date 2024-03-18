package com.microservice.mail.email.consumers;

import com.microservice.mail.email.dtos.EmailDTO;
import com.microservice.mail.email.models.EmailModel;
import com.microservice.mail.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;


    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        EmailModel email = new EmailModel();
        BeanUtils.copyProperties(emailDTO, email);
        EmailModel savedEmail = emailService.sendingEmail(email);
        System.out.println(savedEmail.getStatusEmail());
    }
}
