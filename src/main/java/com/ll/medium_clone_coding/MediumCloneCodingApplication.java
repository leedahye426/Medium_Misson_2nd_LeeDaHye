package com.ll.medium_clone_coding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MediumCloneCodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediumCloneCodingApplication.class, args);
    }

}
