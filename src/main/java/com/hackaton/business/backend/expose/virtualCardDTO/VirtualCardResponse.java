package com.hackaton.business.backend.expose.virtualCardDTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VirtualCardResponse {

    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private double authorizedAmount;
    private double availableAmount;

}
