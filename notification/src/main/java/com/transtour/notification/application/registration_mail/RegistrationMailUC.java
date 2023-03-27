package com.transtour.notification.application.registration_mail;

import com.transtour.notification.application.registration_mail.command.RegistrationMailCommand;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RegistrationMailUC {

    private final JavaMailSender javaMailSender;

    public RegistrationMailUC(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(RegistrationMailCommand command) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(command.getFrom());
        message.setTo(command.getTo());
        message.setSubject(command.getSubject());
        message.setText(command.getBody());
        javaMailSender.send(message);
    }
}
