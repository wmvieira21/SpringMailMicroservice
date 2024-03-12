package com.microservice.mail.email.controllers;

import com.microservice.mail.email.dtos.EmailDTO;
import com.microservice.mail.email.services.EmailService;
import jakarta.validation.Valid;
import com.microservice.mail.email.models.EmailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        EmailModel email = new EmailModel();
        BeanUtils.copyProperties(emailDTO, email);
        EmailModel savedEmail = emailService.sendingEmail(email);
        return new ResponseEntity<>(savedEmail, HttpStatus.CREATED);
    }
}
