package com.microservice.mail.email.controllers;

import com.microservice.mail.email.dtos.EmailDTO;
import com.microservice.mail.email.services.EmailService;
import jakarta.validation.Valid;
import com.microservice.mail.email.models.EmailModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/email")
public class EmailController {

    private Logger logger = LogManager.getLogger(EmailController.class);
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        EmailModel email = new EmailModel();
        BeanUtils.copyProperties(emailDTO, email);
        EmailModel savedEmail = emailService.sendingEmail(email);
        return new ResponseEntity<>(savedEmail, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{emailID}")
    public ResponseEntity<EmailModel> getEmailByID(@PathVariable(value = "emailID") UUID emailID) {
        EmailModel email = emailService.getEmailByID(emailID);
        return ResponseEntity.ok().body(email);
    }

    @GetMapping
    public ResponseEntity<Page<EmailModel>> getAllEmails(@PageableDefault(page = 0, size = 10, sort = "sendDateEmail") Pageable pageable) {
        logger.trace("trace");
        logger.debug("degub");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
        return ResponseEntity.ok().body(emailService.getAllEmails(pageable));
    }
}
