package com.home.conferenceboot.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {
	
	/*
	 * Any method can return bean definition will get 
	 * stored in spring context. 
	 * Spring looks for if a DataSource bean already exist (the default one provided by Spring Boot
	 * if it does, it will replace it with the data source return from the method
	 */
	
//	@Bean
//	public DataSource dataSource() {
//		DataSourceBuilder builder = DataSourceBuilder.create();
//		builder.url("");
//		return builder.build();
//	}	
}
