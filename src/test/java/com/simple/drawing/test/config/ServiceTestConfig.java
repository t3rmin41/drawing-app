package com.simple.drawing.test.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.simple.drawing.service", "com.simple.drawing.mapper", "com.simple.drawing.repository"})
@EnableTransactionManagement
public class ServiceTestConfig {

  private String activeProfile = "prod";
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Bean(name = "dataSource")
  public DriverManagerDataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    if ("prod".equals(activeProfile)) {
      //driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
      //driverManagerDataSource.setUrl("jdbc:postgresql://ec2-54-217-217-131.eu-west-1.compute.amazonaws.com:5432/d6tvf2m5k6d7ok");
      //driverManagerDataSource.setUsername("cmxewoxmnxboqn"); // using account credentials
      //driverManagerDataSource.setPassword("c136205c6e9650e4adadfe1d9a674875a536eab8f36bd647c71b3e8c6fa34b7c");
      
      driverManagerDataSource.setDriverClassName("org.h2.Driver");
      driverManagerDataSource.setUrl("jdbc:h2:./src/test/resources/db/prod/bin;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
      driverManagerDataSource.setUsername("sa");
      driverManagerDataSource.setPassword("pr0ds3cr3t");
    } else if ("test".equals(activeProfile)) {
      driverManagerDataSource.setDriverClassName("org.h2.Driver");
      driverManagerDataSource.setUrl("jdbc:h2:./src/test/resources/db/test/bin;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
      driverManagerDataSource.setUsername("sa");
      driverManagerDataSource.setPassword("t3sts3cr3t");
    } else if ("dev".equals(activeProfile)) {
      driverManagerDataSource.setDriverClassName("org.h2.Driver");
      driverManagerDataSource.setUrl("jdbc:h2:./src/test/resources/db/dev/bin;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
      driverManagerDataSource.setUsername("sa");
      //driverManagerDataSource.setPassword("d3vs3cr3t");
    }
    return driverManagerDataSource;
  }
  


  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
     JpaTransactionManager transactionManager = new JpaTransactionManager();
     transactionManager.setEntityManagerFactory(emf);

     return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
     return new PersistenceExceptionTranslationPostProcessor();
  }
  
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan(new String[] { "com.simple.drawing.repository", "com.simple.drawing.jpa" });

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());

    return em;
  }
  
  private Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "update");
    if ("prod".equals(activeProfile)) {
      //properties.setProperty("hibernate.show_sql", "false");
      //properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL92Dialect");
      
      properties.setProperty("hibernate.show_sql", "false");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
      properties.setProperty("h2.console.settings.web-allow-others", "true");
      
    } else if ("test".equals(activeProfile)) {
      properties.setProperty("hibernate.show_sql", "true");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
      properties.setProperty("h2.console.settings.web-allow-others", "true");
    } else if ("dev".equals(activeProfile)) {
      properties.setProperty("hibernate.show_sql", "true");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
      properties.setProperty("h2.console.settings.web-allow-others", "true");
    }
    return properties;
 }
  
}
