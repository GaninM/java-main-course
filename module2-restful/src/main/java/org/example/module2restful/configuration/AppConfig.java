package org.example.module2restful.configuration;

import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@ComponentScan(basePackages = "org.example.module2restful")
@PropertySource(value = "classpath:application-${spring.profiles.active}.properties")
public class AppConfig {

  private final Environment env;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
    dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
    return dataSource;
  }

  @Bean
  @DependsOn("flyway")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("org.example.module2restful.repository.entity");
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean(initMethod = "migrate")
  public Flyway flyway() {
    Flyway flyway = Flyway.configure()
        .dataSource(dataSource())
        .locations(
            "classpath:db/migration/" + env.getActiveProfiles()[0],
            "classpath:db/migration/common"
        )
        .placeholders(Map.of(
            "profile.user.name", env.getProperty("profile.user.name", "Default User"),
            "profile.user.email", env.getProperty("profile.user.email", "default@example.com")
        ))
        .load();
    flyway.migrate();
    return flyway;
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
    properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql", "false"));
    properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql", "false"));
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "none"));
    return properties;
  }

  @Bean
  public HibernateTransactionManager transactionManager() {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory().getObject());
    return txManager;
  }
}