package com.vn.osp.notarialservices.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by nmha on 3/21/2017.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    @Value("${mongoDBName}")
    private String databaseName;

    @Value("${mongoDBHostName}")
    private String hostName;

    @Value("${mongoDBPortValue}")
    private int port;

    @Value("${mongoDBUserName}")
    private String username;

    @Value("${mongoDBPassword}")
    private String password;

//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }

    @PostConstruct
    public void postConstruct() {
        MongoDBConfig.databaseName = databaseName;
        MongoDBConfig.hostName = hostName;
        MongoDBConfig.port = port;
        MongoDBConfig.username = username;
        MongoDBConfig.password = password;
    }
}
