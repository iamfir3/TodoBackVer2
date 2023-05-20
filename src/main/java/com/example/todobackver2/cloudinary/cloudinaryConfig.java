package com.example.todobackver2.cloudinary;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class cloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dyvgnrswn",
                "api_key", "253456739655181",
                "api_secret", "5qdrIMVvkQdHdkzBSe-aDrY-zbg"));
    }
}
