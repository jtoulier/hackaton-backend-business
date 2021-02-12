package com.hackaton.business.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * The type Application properties.
 *
 * @author Jean Pier Barbieri
 */
@Configuration
@Getter
@Setter
public class ApplicationProperties {

  @NotNull
  @Value("${application.datasource.driverClassName}")
  private String driverClassName;

  @NotNull
  @Value("${application.datasource.url}")
  private String url;

  @NotNull
  @Value("${application.datasource.username}")
  private String username;

  @NotNull
  @Value("${application.datasource.password}")
  private String password;

}
