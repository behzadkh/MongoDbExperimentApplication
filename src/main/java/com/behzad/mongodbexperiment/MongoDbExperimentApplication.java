package com.behzad.mongodbexperiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableMongoRepositories
public class MongoDbExperimentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDbExperimentApplication.class, args);
    }

}
