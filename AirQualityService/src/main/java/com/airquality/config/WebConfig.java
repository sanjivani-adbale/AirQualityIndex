package com.airquality.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Angular's default development URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add methods you use
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // If cookies are used
    }
}
