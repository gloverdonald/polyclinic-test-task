package ru.itis.polyclinictesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableFeignClients
public class PolyclinicTestTaskApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(PolyclinicTestTaskApplication.class, args);
    }
}