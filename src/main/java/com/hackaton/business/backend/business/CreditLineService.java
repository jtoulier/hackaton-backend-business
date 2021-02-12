package com.hackaton.business.backend.business;

import com.hackaton.business.backend.expose.creditLineDTO.CreditLineResponse;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

@Service
public interface CreditLineService {

    Single<CreditLineResponse> getCreditLine(String dni);

}
