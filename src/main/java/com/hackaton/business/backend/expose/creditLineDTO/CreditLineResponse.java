package com.hackaton.business.backend.expose.creditLineDTO;

import com.hackaton.business.backend.expose.virtualCardDTO.VirtualCardResponse;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CreditLineResponse {
    private double availableAmount;
    private List<VirtualCardResponse> virtualCardResponses;
}
