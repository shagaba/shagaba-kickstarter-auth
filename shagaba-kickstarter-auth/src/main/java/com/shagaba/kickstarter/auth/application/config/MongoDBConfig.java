package com.shagaba.kickstarter.auth.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * AbstractMongoConfiguration provides a default set of infrastructure components and provides callback methods to tweak the configuration as necessary.
 * mongo() creates a MongoClient object which contains an internal connection pool. 
 * This connects to the database returned by getDatabaseName() on the specified host using the default port (27017).
 * @author Shai
 *
 */
@Configuration
@PropertySource("classpath:mongodb.properties")
@EnableMongoAuditing(auditorAwareRef = "customAuditorAware")
@EnableMongoRepositories(basePackages = { "com.shagaba.kickstarter.auth.core.repository" })
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Value("${mongodb.db}")
    private String databaseName;
    
    @Value("${mongodb.url}")
    private String url;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoClientURI mci = new MongoClientURI(url);
        return new MongoClient(mci);
    }

}
