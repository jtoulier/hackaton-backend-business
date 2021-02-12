package com.hackaton.business.backend.business.impl;

import com.hackaton.business.backend.business.CreditLineService;
import com.hackaton.business.backend.expose.creditLineDTO.CreditLineResponse;
import com.hackaton.business.backend.repository.CreditLineRepository;
import com.hackaton.business.backend.repository.entity.CreditLine;
import com.hackaton.business.error.ApiException;
import com.hackaton.business.error.ApiExceptionCode;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditLineImpl implements CreditLineService {

    private final CreditLineRepository creditLineRepository;

    @Override
    public Single<CreditLineResponse> getCreditLine(String dni) {
        return Single
                .fromCallable(() -> {
                    Optional<CreditLine> creditLine = this.creditLineRepository.findCreditLineByDni(dni);

                    if(creditLine.isEmpty()){
                        throw new ApiException(ApiExceptionCode.CREDIT_LINE_NOT_FOUND);
                    }

                    return CreditLineResponse
                            .builder()
                            .availableAmount(creditLine.get().getAuthorizedAmount() - creditLine.get().getUsedAmount())
                            .build();


                })
                .doOnSuccess(e -> log.info("Finished CreditLineImpl.getCreditLine"))
                .doOnError(ex -> log.error("Error on CreditLineImpl.getCreditLine"))
                .onErrorResumeNext(ex -> {
                    log.error("Error");
                    return Single.error(ex);
                });

    }
}
