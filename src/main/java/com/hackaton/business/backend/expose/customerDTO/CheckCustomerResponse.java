package com.hackaton.business.backend.expose.customerDTO;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CheckCustomerResponse {

    private String dni;
    private String firstSurname;
    private String secondSurname;
    private String givenName;

}
