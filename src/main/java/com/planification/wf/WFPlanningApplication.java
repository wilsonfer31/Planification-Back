package com.planification.wf;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties()
public class WFPlanningApplication {
    private static final Logger logger = LoggerFactory.getLogger(WFPlanningApplication.class);
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private final Environment env;

    public WFPlanningApplication(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void initApplication() {

        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains("prod") && activeProfiles.contains("dev")) {
            logger.error("Error configuration " +
                    "Impossiblité d'avoir profil dev et prod au meme temps.");
        }

        env.getActiveProfiles();
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WFPlanningApplication.class);
        addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logConfiguredValues(env);

    }

    private static void logConfiguredValues(Environment environment) {
        String frontUrl = environment.getProperty("front.url.value");
        String webhookUrl = environment.getProperty("spring.discord.webhook");
        String database = environment.getProperty("spring.datasource.url");


        logger.info("Front URL: " + frontUrl);
        logger.info("Connected to Discord Webhook: " + webhookUrl);
        logger.info("Connected to database: " + database);

    }

    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap<>();
        defProperties.put(SPRING_PROFILE_DEFAULT, "dev");
        app.setDefaultProperties(defProperties);
    }

}
