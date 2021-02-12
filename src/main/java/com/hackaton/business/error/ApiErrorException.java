package com.hackaton.business.error;

import lombok.*;

import java.util.Date;
import java.util.Map;

/**
 * The type Api error.
 *
 * @author Jean Pier Barbieri
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorException {
  private String code;
  private String message;
  private Date errorTimeStamp;
  private Map<String, Object> details;

}
