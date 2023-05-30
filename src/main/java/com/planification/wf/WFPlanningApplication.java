package com.planification.wf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WFPlanningApplication {
    private static final Logger logger = LoggerFactory.getLogger(WFPlanningApplication.class);


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(WFPlanningApplication.class, args);
        logConfiguredValues(applicationContext);

    }

    private  static void logConfiguredValues(ApplicationContext context) {
        Environment environment = context.getEnvironment();
        String frontUrl = environment.getProperty("front.url.value");
        String webhookUrl = environment.getProperty("spring.discord.webhook");
        String database = environment.getProperty("spring.datasource.url");


        logger.info("Front URL: " + frontUrl);
        logger.info("Connected to Discord Webhook: " + webhookUrl);
        logger.info("Connected to database: " + database);

    }



}
