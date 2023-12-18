package com.memorybox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MemoryBoxMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemoryBoxMainApplication.class, args);
    }

}
