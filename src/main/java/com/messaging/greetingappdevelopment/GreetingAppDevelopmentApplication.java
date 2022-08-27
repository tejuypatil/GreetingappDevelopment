package com.messaging.greetingappdevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.messaging.greetingappdevelopment.repository")
public class GreetingAppDevelopmentApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(GreetingAppDevelopmentApplication.class, args);
    }

}
