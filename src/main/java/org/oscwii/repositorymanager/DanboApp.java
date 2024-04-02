package org.oscwii.repositorymanager;

import org.jdbi.v3.spring5.EnableJdbiRepositories;
import org.oscwii.repositorymanager.config.repoman.RepoManConfig;
import org.oscwii.repositorymanager.services.FeaturedApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@EnableJdbiRepositories
@ConfigurationPropertiesScan(value = "org.oscwii.repositorymanager.config.repoman")
public class DanboApp
{
    @Autowired
    private RepoManConfig config;
    @Autowired
    private RepositoryIndex index;
    @Autowired
    private FeaturedApp featuredApp;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup()
    {
        // Load the repository without updating apps
        index.initialize();

        // Pick a featured app
        featuredApp.pickFeaturedApp();
    }

    @GetMapping("/")
    public String hello(Model model)
    {
        model.addAttribute("app_count", index.getContents().size())
                .addAttribute("repository_name", index.getInfo().name())
                .addAttribute("repository_provider", index.getInfo().provider())
                .addAttribute("git_url", "TODO") // TODO
                .addAttribute("base_url", config.getBaseUrl());
        return "hello_world.ftl";
    }

    // TODO remove debug
    @GetMapping("/debug")
    @ResponseBody
    public String debug()
    {
        index.index(true);
        return "running";
    }

    public static void main(String[] args)
    {
        SpringApplication.run(DanboApp.class, args);
    }
}
