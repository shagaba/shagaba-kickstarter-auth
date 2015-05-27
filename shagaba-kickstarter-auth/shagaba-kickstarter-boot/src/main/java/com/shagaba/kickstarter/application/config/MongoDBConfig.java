package com.shagaba.kickstarter.application.config;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.shagaba.kickstarter.application.init.InitializeMongoDB;

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
@EnableMongoRepositories(basePackages = { "com.shagaba.kickstarter" })
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

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean jackson2RepositoryPopulator() {
	    Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
	    factory.setResources(new Resource[] { new ClassPathResource("/init/repository/security.json") });
	    return factory;
    }
    
    @Bean
    public InitializeMongoDB initializeMongoDB() {
        return new InitializeMongoDB();
    }
    

}
