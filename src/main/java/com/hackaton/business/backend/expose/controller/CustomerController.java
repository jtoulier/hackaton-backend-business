package com.hackaton.business.backend.expose.controller;


import com.hackaton.business.backend.business.CreditLineService;
import com.hackaton.business.backend.business.CustomerService;
import com.hackaton.business.backend.business.VirtualCardService;
import com.hackaton.business.backend.expose.creditLineDTO.CreditLineResponse;
import com.hackaton.business.backend.expose.customerDTO.CheckCustomerRequest;
import com.hackaton.business.backend.expose.customerDTO.CheckCustomerResponse;
import com.hackaton.business.backend.expose.virtualCardDTO.VirtualCardResponse;
import com.hackaton.business.backend.expose.virtualCardDTO.VirtualCardSavedResquest;
import io.reactivex.*;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CreditLineService creditLineService;
    private final VirtualCardService virtualCardService;

    @PostMapping(value = "/check-customers",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Maybe<ResponseEntity<CheckCustomerResponse>> checkCustomer(
            @RequestBody CheckCustomerRequest checkCustomerRequest
            ) {
        log.info("Starting CustomerController.checkCustomer");
        return this.customerService.checkCustomer(checkCustomerRequest);
    }

    @PostMapping(value = "/virtual-cards",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Completable saveVirtualCard(
            @RequestBody VirtualCardSavedResquest virtualCardSavedResquest
    ) {
        log.info("Starting CustomerController.saveVirtualCard");
        return this.virtualCardService.saveVirtualCard(virtualCardSavedResquest);
    }

    @GetMapping(value = "/credit-lines/{dni}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Single<CreditLineResponse> getCreditLine(@PathVariable(value = "dni") String dni) {
        log.info("Starting CustomerController.findCreditLineByDni");
        return this.creditLineService.getCreditLine(dni);
    }


}
