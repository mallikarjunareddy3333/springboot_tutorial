package com.springboot.tutorial.datasource;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Autowired
	private Environment env;

	@Bean(name = "dbProductService")
	@ConfigurationProperties(prefix = "spring.db-product-service")
	@Primary
	public DataSource createProductServiceDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcProductService")
	@Autowired
	public JdbcTemplate createJdbcTemplate_ProductService(@Qualifier("dbProductService") DataSource productServiceDS) {
		return new JdbcTemplate(productServiceDS);
	}
	
	@Bean(name="EntityManagerProductService")
	public LocalContainerEntityManagerFactoryBean EntityManagerProductService() {
	    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	    em.setDataSource(createProductServiceDataSource());
	    em.setPackagesToScan(new String[] {"com.springboot.tutorial"});

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    em.setJpaVendorAdapter(vendorAdapter);
	    HashMap<String, Object> properties = new HashMap();
	    properties.put("hibernate.show_sql", env.getProperty("spring.jpa.hibernate.show_sql"));
	    properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.hbm2ddl.auto"));
	    properties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.dialect"));
	    em.setJpaPropertyMap(properties);

	    return em;
	}

	@Bean(name = "ProductServiceTransactionManager")
	public PlatformTransactionManager ProductServiceTransactionManager() {
	    JpaTransactionManager tm = new JpaTransactionManager();
	    tm.setEntityManagerFactory(EntityManagerProductService().getObject());
	    return tm;
	}

}
