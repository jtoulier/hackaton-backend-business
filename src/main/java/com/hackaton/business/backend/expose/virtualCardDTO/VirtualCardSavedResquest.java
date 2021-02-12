package com.hackaton.business.backend.expose.virtualCardDTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VirtualCardSavedResquest {

    private String dni;
    private String expirationDate;
    private double amount;

}
