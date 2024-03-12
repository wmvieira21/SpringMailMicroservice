package com.microservice.mail.email.conf;

import jakarta.mail.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public JavaMailSender javaMailSender() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.googlemail.com");
        mailSender.setPort(587);
        mailSender.setUsername("williamvieira334@gmail.com");
        mailSender.setPassword("oesoryryuvgfwtej");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
