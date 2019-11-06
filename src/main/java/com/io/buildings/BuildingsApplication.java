package com.io.buildings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BuildingsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuildingsApplication.class, args);
    }

}
