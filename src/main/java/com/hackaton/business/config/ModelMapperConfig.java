package com.hackaton.business.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Model mapper config.
 *
 * @author Jean Pier Barbieri
 */
@Configuration
public class ModelMapperConfig {

  /**
   * Model mapper model mapper.
   *
   * @return the model mapper
   */
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }


}
