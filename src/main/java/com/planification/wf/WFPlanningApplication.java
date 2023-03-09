package com.planification.wf;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class WFPlanningApplication {

    @Value("${front.url.value}")
    private String frontUrl;


    public static void main(String[] args) {
        SpringApplication.run(WFPlanningApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer myMvcConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins( frontUrl)
                        .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS");
            }

        };
    }

}
