package com.hackaton.business.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Api exception advice.
 *
 * @author Jean Pier Barbieri
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionAdvice {

  private final ApiErrorProperties apiErrorProperties;
  private static final String LOG_MESSAGE = "Excepcion inesperada: {}";


  /**
   * Throw general exception response entity.
   *
   * @param exception the exception
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorException> throwGeneralException(Exception exception) {
    ApiErrorException fieldsError = this.apiErrorProperties
            .getApiError(ApiExceptionCode.FIELDS_VALIDATION_ERROR);
    fieldsError.setErrorTimeStamp(new Date());
    if (exception instanceof MethodArgumentNotValidException) {
      MethodArgumentNotValidException manvException = (MethodArgumentNotValidException) exception;
      Map<String, Object> body = new LinkedHashMap<>();
      List<String> errors = manvException.getBindingResult().getFieldErrors().stream()
              .map(FieldError::getDefaultMessage).collect(Collectors.toList());
      body.put("errors", errors);
      fieldsError.setDetails(body);
      log.info(LOG_MESSAGE, fieldsError.getDetails(), exception);
      return new ResponseEntity<>(fieldsError, HttpStatus.INTERNAL_SERVER_ERROR);
    } else if (exception instanceof WebExchangeBindException) {
      WebExchangeBindException error = (WebExchangeBindException) exception;
      Map<String, Object> fieldErrors = new HashMap<>();
      List<String> errors = error.getFieldErrors().stream()
              .map(p -> "Campo " + p.getField() + ": " + p.getDefaultMessage())
              .collect(Collectors.toList());
      fieldErrors.put("errors", errors);
      fieldsError.setDetails(fieldErrors);
      log.info(LOG_MESSAGE, fieldsError.getDetails(), exception);
      return new ResponseEntity<>(fieldsError, HttpStatus.INTERNAL_SERVER_ERROR);
    } else if (exception instanceof ServerWebInputException) {
      Map<String, Object> body = new LinkedHashMap<>();
      ServerWebInputException swiException = (ServerWebInputException) exception;
      body.put("error", swiException.getReason());
      fieldsError.setDetails(body);
      log.info(LOG_MESSAGE, fieldsError.getDetails(), exception);
      return new ResponseEntity<>(fieldsError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.info("Excepcion inesperada: ", exception);
    ApiErrorException apiErrorException = this.apiErrorProperties.getApiError(ApiExceptionCode.TECHNICAL_ERROR);
    apiErrorException.setErrorTimeStamp(new Date());
    return new ResponseEntity<>(apiErrorException, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Throw api exception response entity.
   *
   * @param apiException the api exception
   * @return the response entity
   */
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiErrorException> throwApiException(ApiException apiException) {
    log.info(LOG_MESSAGE, apiException.getDetails(), apiException.getThrowable());
    ApiErrorException apiErrorException = this.apiErrorProperties.getApiError(apiException.getApiExceptionCode());
    apiErrorException.setErrorTimeStamp(new Date());
    return new ResponseEntity<>(apiErrorException, HttpStatus.BAD_REQUEST);
  }


}
