package com.mt.zt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mt.zt.utils.PropertiesUtils;

@Configuration
@EnableMongoRepositories(basePackages = { "com.mt.zt.mongodb.repository" })
@ComponentScan(basePackages={"com.mt.zt.mongodb.repository"})
@EnableMongoAuditing
public class MongoDbConfiguration extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "ztDb";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		//MongoClientOptions opts = MongoClientOptions.builder().build();
		return new MongoClient("127.0.0.1", 27017);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(mongo(), getDatabaseName());
	}

	
	@Bean
	public PropertySourcesPlaceholderConfigurer getProperties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new Resource[] {
				new ClassPathResource("business/config.properties")};
		PropertiesUtils.addPropertiesFile(resources);
		pspc.setLocations(resources);
		return pspc;
	}

}
