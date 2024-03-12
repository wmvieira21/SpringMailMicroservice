package com.microservice.mail.email.services;

import com.microservice.mail.email.repositories.EmailRepository;
import com.microservice.mail.email.enums.StatusEmail;
import com.microservice.mail.email.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailModel sendingEmail(EmailModel email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            javaMailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException ex) {
            email.setStatusEmail(StatusEmail.ERROR);
            email.setError(ex.getMessage());
        } finally {
            return emailRepository.save(email);
        }
    }
}

