package com.buildweek.usemytechstuff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UseMyTechStuffApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(UseMyTechStuffApplication.class,
            args);
    }

}
