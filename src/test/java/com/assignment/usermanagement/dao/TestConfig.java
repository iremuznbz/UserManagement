package com.assignment.usermanagement.dao;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ComponentScan(basePackages = {"com.assignment.usermanagement.controller",
        "com.assignment.usermanagement.dao",
        "com.assignment.usermanagement.model",
        "com.assignment.usermanagement.service"})
public class TestConfig {

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp("127.0.0.1");
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "embedded_db");
        return mongoTemplate;
    }
}

