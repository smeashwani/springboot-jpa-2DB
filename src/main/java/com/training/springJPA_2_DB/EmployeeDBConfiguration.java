package com.training.springJPA_2_DB;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.training.springJPA_2_DB.dao.employee.repo", 
    entityManagerFactoryRef = "primaryEntityManagerFactory", 
    transactionManagerRef = "primaryTransactionManager"
)
public class EmployeeDBConfiguration {
	
		@Primary
	    @Bean(name = "primaryDataSourceProperties")
	    @ConfigurationProperties(prefix = "spring.datasource-primary")
	    public DataSourceProperties primaryDataSourceProperties() {
	        return new DataSourceProperties();
	    }
	 	
		@Primary
	    @Bean(name = "primaryDataSource")
	    public DataSource primaryDataSource(@Qualifier("primaryDataSourceProperties") DataSourceProperties primaryDataSourceProperties) {
	        return primaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	    }
	 	
		@Primary
	    @Bean(name = "primaryEntityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
	            EntityManagerFactoryBuilder primaryEntityManagerFactoryBuilder, @Qualifier("primaryDataSource") DataSource primaryDataSource) {

	        Map<String, String> primaryJpaProperties = new HashMap<>();
	        primaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

	        return primaryEntityManagerFactoryBuilder
	                .dataSource(primaryDataSource)
	                .packages("com.training.springJPA_2_DB.model.employee")
	                .persistenceUnit("primaryDataSource")
	                .properties(primaryJpaProperties)
	                .build();
	    }
	 	
		@Primary
	    @Bean(name = "primaryTransactionManager")
	    public PlatformTransactionManager primaryTransactionManager(
	            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {

	        return new JpaTransactionManager(primaryEntityManagerFactory);
	    }
}
