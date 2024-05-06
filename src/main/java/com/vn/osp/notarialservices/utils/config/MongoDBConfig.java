package com.vn.osp.notarialservices.utils.config;

import com.mongodb.*;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by nmha on 3/2/2017.
 */


@Component
public class MongoDBConfig {

    public static String databaseName;

    public static String hostName;

    public static int port;

    public static String username;

    public static String password;

    public MongoClient getMongoClientByDefaultRole() throws UnknownHostException {

        MongoClient mongoClient = new MongoClient(hostName, port);
        return mongoClient;
    }

    public MongoClient getMongoClientByUser() throws UnknownHostException {
        MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(username, databaseName, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(hostName, port), Arrays.asList(mongoCredential));
        return mongoClient;
    }

}
