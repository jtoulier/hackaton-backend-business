package com.hackaton.business.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;


/**
 * The type Data source config.
 *
 * @author Jean Pier Barbieri
 */
@Configuration
@Lazy
@Slf4j
public class DataSourceConfig {

  /**
   * Data source data source.
   *
   * @param applicationProperties the application properties
   * @return the data source
   */
  @Bean
  DataSource dataSource(ApplicationProperties applicationProperties) {

    DataSourceBuilder<? extends DataSource> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName(applicationProperties.getDriverClassName());
    dataSourceBuilder.url(applicationProperties.getUrl());
    dataSourceBuilder.username(applicationProperties.getUsername());
    dataSourceBuilder.password(applicationProperties.getPassword());

    return dataSourceBuilder.build();
  }


}
