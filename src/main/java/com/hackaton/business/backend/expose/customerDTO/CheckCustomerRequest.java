package com.hackaton.business.backend.expose.customerDTO;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CheckCustomerRequest {

    private String dni;
    private String password;
}
