package com.hackaton.business.backend.business.impl;

import com.hackaton.business.backend.business.CreditLineService;
import com.hackaton.business.backend.business.VirtualCardService;
import com.hackaton.business.backend.expose.virtualCardDTO.VirtualCardResponse;
import com.hackaton.business.backend.expose.virtualCardDTO.VirtualCardSavedResquest;
import com.hackaton.business.backend.repository.AvailableCardRepository;
import com.hackaton.business.backend.repository.CreditLineRepository;
import com.hackaton.business.backend.repository.VirtualCardRepository;
import com.hackaton.business.backend.repository.entity.AvailableCard;
import com.hackaton.business.backend.repository.entity.CreditLine;
import com.hackaton.business.backend.repository.entity.VirtualCard;
import com.hackaton.business.error.ApiException;
import com.hackaton.business.error.ApiExceptionCode;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class VirtualCardImpl implements VirtualCardService {

    private final VirtualCardRepository virtualCardRepository;
    private final AvailableCardRepository availableCardRepository;
    private final CreditLineRepository creditLineRepository;
    private final CreditLineService creditLineService;

    @Override
    public Completable saveVirtualCard(VirtualCardSavedResquest virtualCardSavedResquest) {
        return this.creditLineService.getCreditLine(virtualCardSavedResquest.getDni())
                .flatMap(p-> { if (p.getAvailableAmount() < virtualCardSavedResquest.getAmount())
                        return Single.error(new ApiException(ApiExceptionCode.NOT_ENOUGH_CREDIT_LINE));
                    return Single.just(this.availableCardRepository.getAllByAvailable());
                })
                .flatMapCompletable( a -> {
                    if(a.isEmpty())
                        return Completable.error(new ApiException(ApiExceptionCode.NOT_AVAILABLE_CARD_FOUND));

                    VirtualCard virtualCard = VirtualCard
                            .builder()
                            .cvv(a.get(0).getCvv())
                            .cardNumber(a.get(0).getCardNumber())
                            .dni(virtualCardSavedResquest.getDni())
                            .authorizedAmount(virtualCardSavedResquest.getAmount())
                            .expirationDate(LocalDate.parse(virtualCardSavedResquest.getExpirationDate()))
                            .usedAmount(0)
                            .build();

                    this.virtualCardRepository.save(virtualCard);

                    //UPDATING CREDIT-LINE
                    Optional<CreditLine> creditLineOptional = this.creditLineRepository.findCreditLineByDni(virtualCardSavedResquest.getDni());
                    CreditLine creditLine = creditLineOptional.get();
                    creditLine.setUsedAmount(creditLine.getUsedAmount() + virtualCardSavedResquest.getAmount());
                    this.creditLineRepository.save(creditLine);

                    //UPDATE CARD STATUS
                    AvailableCard availableCard = a.get(0);
                    availableCard.setAvailable("N");
                    this.availableCardRepository.save(availableCard);

                    return Completable.complete();
                })
                .doOnComplete(() -> log.info("Finished VirtualCardImpl.saveVirtualCard"))
                .doOnError(ex -> log.error("Error on VirtualCardImpl.saveVirtualCard"))
                .onErrorResumeNext(ex -> {
                    log.error("Error");
                    return Completable.error(ex);
                });
    }
}
