package com.creativelabs.scriptscreator.service;

import com.creativelabs.scriptscreator.config.AdminConfig;
import com.creativelabs.scriptscreator.domain.Npc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private NpcService npcService;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your npc");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending npc to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Thank you for using Crud App!");
        context.setVariable("info_company_name", adminConfig.getInfoCompanyName());
        context.setVariable("info_company_goal", adminConfig.getInfoCompanyGoal());
        context.setVariable("info_company_email", adminConfig.getInfoCompanyEmail());
        context.setVariable("separator", " ");
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String dailyNpcReportEmail(String message) {
        List<Npc> npcList = npcService.getAllNpcs();
        int size = npcService.getAllNpcs().size();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("goodbye_message", "Thank you for using Scripts Creator App!");
        context.setVariable("info_company_name", adminConfig.getInfoCompanyName());
        context.setVariable("info_company_goal", adminConfig.getInfoCompanyGoal());
        context.setVariable("info_company_email", adminConfig.getInfoCompanyEmail());
        context.setVariable("separator", ", ");
        context.setVariable("is_friend", false);
        context.setVariable("howManyNpc", size);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("npc_list", npcList);
        return templateEngine.process("mail/weekly-npc-report-mail", context);
    }
}
