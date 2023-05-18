package com.example.demo.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person(
                    "chrisnolan123",
                    "nolan@email.com",
                    "chris",
                    "nolan"
            )));
            log.info("Preloading " + repository.save(new Person(
                    "martinscorc123",
                    "martin@email.com",
                    "martin",
                    "scorcese"
            )));
        };
    }
}