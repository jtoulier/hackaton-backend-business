package com.hackaton.business.backend.expose.creditLineDTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreditLineResponse {
    private double availableAmount;
}
