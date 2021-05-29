package com.creativelabs.scriptscreator.scheduler;

import com.creativelabs.scriptscreator.config.AdminConfig;
import com.creativelabs.scriptscreator.domain.Mail;
import com.creativelabs.scriptscreator.repository.NpcRepository;
import com.creativelabs.scriptscreator.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Npc: Once a day email";
    private static final String REPORT_SUBJECT = "Daily npc report";

    @Autowired
    private EmailService emailService;

    @Autowired
    private NpcRepository npcRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail() {
        long size = npcRepository.count();
        String message = "Currently in database you got: " + size + " npc.";
        if (size == 1) {
            message = "Currently in database you got: " + size + " npc.";
        }
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                message)
        );
    }

    @Scheduled(cron = "0 0 09 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void dailyNpcReportEmail() {
        long size = npcRepository.count();
        String message = "Currently in database you got: " + size + " npc.";
        if (size == 1) {
            message = "Currently in database you got: " + size + " npc.";
        }
        emailService.sendDailyNpcReport(new Mail(
                adminConfig.getAdminMail(),
                REPORT_SUBJECT,
                message)
        );
    }
}

