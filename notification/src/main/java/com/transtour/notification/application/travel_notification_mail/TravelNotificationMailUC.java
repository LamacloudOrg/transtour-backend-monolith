package com.transtour.notification.application.travel_notification_mail;

import com.transtour.notification.application.travel_notification_mail.command.TravelNotificationMailCommand;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class TravelNotificationMailUC {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Value("{spring.mail.username}")
    private String from;

    public TravelNotificationMailUC(JavaMailSender javaMailSender, Configuration configuration) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    public void send(TravelNotificationMailCommand command) throws MessagingException, IOException, TemplateException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        // add attachment
        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        Template t = configuration.getTemplate("travel-notification.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, command.getAttributes());

        helper.setTo(command.getTo());
        helper.setText(html, true);
        helper.setSubject(command.getSubject());
        helper.setFrom(from);
        javaMailSender.send(message);

    }
}
