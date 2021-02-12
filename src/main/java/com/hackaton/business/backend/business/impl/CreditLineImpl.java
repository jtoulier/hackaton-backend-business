package com.hackaton.business.backend.business.impl;

import com.hackaton.business.backend.business.CreditLineService;
import com.hackaton.business.backend.expose.creditLineDTO.CreditLineResponse;
import com.hackaton.business.backend.expose.virtualCardDTO.VirtualCardResponse;
import com.hackaton.business.backend.repository.CreditLineRepository;
import com.hackaton.business.backend.repository.VirtualCardRepository;
import com.hackaton.business.backend.repository.entity.CreditLine;
import com.hackaton.business.backend.repository.entity.VirtualCard;
import com.hackaton.business.error.ApiException;
import com.hackaton.business.error.ApiExceptionCode;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditLineImpl implements CreditLineService {

    private final CreditLineRepository creditLineRepository;
    private final VirtualCardRepository virtualCardRepository;
    private final ModelMapper modelMapper;

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

                }).map(creditLineResponse -> {
                    List<VirtualCard> virtualCards = this.virtualCardRepository.getAllByDni(dni);
                    List<VirtualCardResponse> virtualCardResponses = new ArrayList<>();
                    if (!virtualCards.isEmpty()) {
                        virtualCardResponses = modelMapper.map(virtualCards, new TypeToken<List<VirtualCardResponse>>() {
                        }.getType());
                    }
                    creditLineResponse.setVirtualCardResponses(virtualCardResponses);
                    return creditLineResponse;
                })
                .doOnSuccess(e -> log.info("Finished CreditLineImpl.getCreditLine"))
                .doOnError(ex -> log.error("Error on CreditLineImpl.getCreditLine"))
                .onErrorResumeNext(ex -> {
                    log.error("Error");
                    return Single.error(ex);
                });

    }
}
