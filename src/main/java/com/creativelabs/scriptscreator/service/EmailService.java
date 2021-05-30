package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private MailCreatorService mailCreatorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendWeeklyNpcReport(final Mail mail) {
        LOGGER.info("Starting weekly report email preparation...");
        try {
            javaMailSender.send(createMimeNpcReport(mail));
            LOGGER.info("Weekly report email has been sent.");
        } catch (MailException e) {
            LOGGER.error("Failed to process daily report email sending: ", e.getMessage(), e);
        }
    }
    private MimeMessagePreparator createMimeNpcReport(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.dailyNpcReportEmail(mail.getMessage()), true);
        };
    }
}

