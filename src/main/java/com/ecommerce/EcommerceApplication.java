package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {
    "com.ecommerce"
})
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(EcommerceApplication.class);
        Map<String, Object> defaultProperties = new HashMap<>();
        app.setDefaultProperties(defaultProperties);
        app.run(args);
    }
}
