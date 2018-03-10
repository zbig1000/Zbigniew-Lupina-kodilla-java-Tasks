package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    @Autowired
    private  JavaMailSender javaMailSenderSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailMessage.class);

    public void send(final Mail mail) {
        LOGGER.info("starting email preparation...");
        try {
            javaMailSenderSender.send(createMailMessage(mail));
            LOGGER.info("Email has beeen sent.");
        } catch (MailException e) {
            LOGGER.error("failed email sending ", e.getMessage(), e);
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mail.getToCc().ifPresent(mailMessage::setCc);
        return mailMessage;
    }

}
