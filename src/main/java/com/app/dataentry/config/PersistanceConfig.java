package com.app.dataentry.config;

import java.beans.PropertyVetoException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
		"com.app.dataentry.repositories" }, transactionManagerRef = "transactionManager" )
public class PersistanceConfig {
	
	@Value("${datasource.url}")
	private String url; 
	@Value("${datasource.driverClassName}")
	private String driverClassName; 
	@Value("${datasource.username}")
	private String username; 
	@Value("${datasource.password}")
	private String password; 
	@Value("${datasource.minPoolSize}")
	private int minPoolSize; 
	@Value("${datasource.maxPoolSize}")
	private int maxPoolSize; 
	@Value("${datasource.maxIdleTime}")
	private int maxIdleTime;
	

	@Bean(name = "dataSource")
	@Primary
	public DataSource platformDataSource() {
		try {
			ComboPooledDataSource dataSource = new ComboPooledDataSource();
			dataSource.setJdbcUrl(url);
			dataSource.setDriverClass(driverClassName);
			dataSource.setUser(username);
			dataSource.setPassword(password);
			dataSource.setMinPoolSize(minPoolSize);
			dataSource.setMaxPoolSize(maxPoolSize);
			dataSource.setMaxIdleTime(maxIdleTime);
			return dataSource;
		} catch (PropertyVetoException e) {
			// Error
			e.printStackTrace();
		}
		return null;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.app.dataentry.model").persistenceUnit("app").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
