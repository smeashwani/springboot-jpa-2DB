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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.training.springJPA_2_DB.dao.product.repo", 
    entityManagerFactoryRef = "secondaryEntityManagerFactory", 
    transactionManagerRef = "secondaryTransactionManager"
)
public class ProductDBConfiguration {
	
	    @Bean(name = "secondaryDataSourceProperties")
	    @ConfigurationProperties(prefix = "spring.datasource-secondary")
	    public DataSourceProperties secondaryDataSourceProperties() {
	        return new DataSourceProperties();
	    }
	 	
	    @Bean(name = "secondaryDataSource")
	    public DataSource secondaryDataSource(@Qualifier("secondaryDataSourceProperties") DataSourceProperties secondaryDataSourceProperties) {
	        return secondaryDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
	    }
	 	
	    @Bean(name = "secondaryEntityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
	            EntityManagerFactoryBuilder secondaryEntityManagerFactoryBuilder, @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {

	        Map<String, String> secondaryJpaProperties = new HashMap<>();
	        secondaryJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

	        return secondaryEntityManagerFactoryBuilder
	                .dataSource(secondaryDataSource)
	                .packages("com.training.springJPA_2_DB.model.product")
	                .persistenceUnit("secondaryDataSource")
	                .properties(secondaryJpaProperties)
	                .build();
	    }
	 	
	    @Bean(name = "secondaryTransactionManager")
	    public PlatformTransactionManager secondaryTransactionManager(
	            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {

	        return new JpaTransactionManager(secondaryEntityManagerFactory);
	    }
}
