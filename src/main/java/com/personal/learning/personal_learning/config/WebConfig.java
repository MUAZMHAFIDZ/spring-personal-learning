package com.personal.learning.personal_learning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // Atau ganti sesuai kebutuhan
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // URL pattern "/uploads/**" akan mengarah ke folder fisik di disk "E:/spring-uploads/"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:E:/spring-uploads/");
    }
}
