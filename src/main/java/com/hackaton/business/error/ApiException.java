package com.hackaton.business.error;

import lombok.Getter;

import java.util.Map;

/**
 * The type Api exception.
 *
 * @author Jean Pier Barbieri
 */
@Getter
public class ApiException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private final ApiExceptionCode apiExceptionCode;
  private final Throwable throwable;
  private final int apiHttpStatusCode;
  private final Map<String, Object> details;

  /**
   * Instantiates a new Api exception.
   *
   * @param apiExceptionCode the api exception code
   * @param throwable        the throwable
   */
  public ApiException(ApiExceptionCode apiExceptionCode, Throwable throwable) {
    super(apiExceptionCode.name());
    this.apiHttpStatusCode = 0;
    this.apiExceptionCode = apiExceptionCode;
    this.throwable = throwable;
    this.details = null;
  }

  /**
   * Instantiates a new Api exception.
   *
   * @param apiExceptionCode the api exception code
   */
  public ApiException(ApiExceptionCode apiExceptionCode) {
    super(apiExceptionCode.name());
    this.throwable = new Throwable();
    this.apiHttpStatusCode = 0;
    this.apiExceptionCode = apiExceptionCode;
    this.details = null;
  }

  /**
   * Instantiates a new Api exception.
   *
   * @param apiExceptionCode  the api exception code
   * @param apiHttpStatusCode the api http status code
   * @param details           the details
   */
  public ApiException(ApiExceptionCode apiExceptionCode, int apiHttpStatusCode,
                      Map<String, Object> details) {
    super(apiExceptionCode.name());
    this.throwable = new Throwable();
    this.apiExceptionCode = apiExceptionCode;
    this.apiHttpStatusCode = apiHttpStatusCode;
    this.details = details;
  }

  /**
   * Instantiates a new Api exception.
   *
   * @param apiExceptionCode  the api exception code
   * @param apiHttpStatusCode the api http status code
   */
  public ApiException(ApiExceptionCode apiExceptionCode, int apiHttpStatusCode) {
    super(apiExceptionCode.name());
    this.throwable = new Throwable();
    this.apiExceptionCode = apiExceptionCode;
    this.apiHttpStatusCode = apiHttpStatusCode;
    this.details = null;
  }

  /**
   * Instantiates a new Api exception.
   *
   * @param apiExceptionCode the api exception code
   * @param throwable        the throwable
   * @param details          the details
   */
  public ApiException(ApiExceptionCode apiExceptionCode, Throwable throwable,
                      Map<String, Object> details) {
    super(apiExceptionCode.name());
    this.apiHttpStatusCode = 0;
    this.apiExceptionCode = apiExceptionCode;
    this.throwable = throwable;
    this.details = details;
  }


}
