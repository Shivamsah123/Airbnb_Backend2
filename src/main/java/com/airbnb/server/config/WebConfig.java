package com.airbnb.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get(uploadDir);
        String uploadAbsolutePath = uploadPath.toFile().getAbsolutePath();

        // Maps /images/** requests to the images/ folder on disk.
        // e.g., http://localhost:4000/images/prop_01.jpg -> images/prop_01.jpg
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:" + uploadAbsolutePath + "/");
    }
}
