package com.microservice.mail.email.services;

import com.microservice.mail.email.exceptions.NoDataFound;
import com.microservice.mail.email.repositories.EmailRepository;
import com.microservice.mail.email.enums.StatusEmail;
import com.microservice.mail.email.models.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
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

    public EmailModel getEmailByID(UUID emailID) {
        EmailModel email = emailRepository.findById(emailID).orElseThrow(() -> new NoDataFound("Email not found!"));
        return email;
    }

    public Page<EmailModel> getAllEmails(Pageable pageable) {
        Page<EmailModel> emails = emailRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
        if (emails.isEmpty()) {
            throw new NoDataFound("Emails not found!");
        }
        return emails;
    }
}

