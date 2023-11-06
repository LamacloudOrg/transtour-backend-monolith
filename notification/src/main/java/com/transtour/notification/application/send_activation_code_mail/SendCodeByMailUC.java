package com.transtour.notification.application.send_activation_code_mail;


import com.transtour.notification.application.send_activation_code_mail.command.SendCodeCommand;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendCodeByMailUC {

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Value("{spring.mail.username}")
    private String from;


    public SendCodeByMailUC(
                            JavaMailSender javaMailSender,
                            Configuration configuration
    ) {
        this.javaMailSender = javaMailSender;
        this.configuration = configuration;
    }

    @SneakyThrows
    public void send(SendCodeCommand command) {

        MimeMessage message = javaMailSender.createMimeMessage();

        Map<String, Object> model = new HashMap<>();
        model.put("code", command.getActivationCode());

        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        // add attachment
        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        Template t = configuration.getTemplate("activation-code.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setTo(command.getTo());
        helper.setText(html, true);
        helper.setSubject("Codigo de Activación");
        helper.setFrom(from);

        javaMailSender.send(message);

    }
}
