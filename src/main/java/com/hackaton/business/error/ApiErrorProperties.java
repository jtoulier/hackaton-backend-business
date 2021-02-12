package com.hackaton.business.error;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

/**
 * The type Api error properties.
 *
 * @author Jean Pier Barbieri
 */
@Configuration
@ConfigurationProperties(prefix = "application")
@Setter
public class ApiErrorProperties {
  private Map<ApiExceptionCode, ApiErrorException> errors;

  /**
   * Gets api error.
   *
   * @param apiExceptionCode the api exception code
   * @return the api error
   */
  public ApiErrorException getApiError(ApiExceptionCode apiExceptionCode) {
    if (Objects.isNull(errors) || this.errors.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "");
    }

    ApiErrorException apiErrorException = this.errors.get(apiExceptionCode);
    if (Objects.isNull(apiErrorException)) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "");
    }
    return apiErrorException;
  }
}
