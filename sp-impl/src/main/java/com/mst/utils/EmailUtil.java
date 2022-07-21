package com.mst.utils;

import com.mst.exception.EmailNotSendException;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class EmailUtil {

    private final JavaMailSender mailSender;

    private final FreeMarkerConfigurer freeMarkerConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    public void sendMail(String to, String subject, String templateName, Map<String, Object> data) {
        try {
            log.info("Email send {} to {}", subject, to);
            String mailTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfiguration.getConfiguration().getTemplate(templateName + ".ftl"), data);

            MimeMessagePreparator preparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setSubject(subject);
                messageHelper.setText(mailTemplate, true);
                messageHelper.setTo(to);
                messageHelper.setFrom(from);
            };
            mailSender.send(preparator);
        } catch (IOException | TemplateException | MailException e) {
            log.error("Email send failed", e);
            throw new EmailNotSendException();
        }
    }
}
