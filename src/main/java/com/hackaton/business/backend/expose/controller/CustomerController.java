package com.hackaton.business.backend.expose.controller;


import com.hackaton.business.backend.business.CreditLineService;
import com.hackaton.business.backend.business.CustomerService;
import com.hackaton.business.backend.expose.creditLineDTO.CreditLineResponse;
import com.hackaton.business.backend.expose.customerDTO.CheckCustomerRequest;
import com.hackaton.business.backend.expose.customerDTO.CheckCustomerResponse;
import io.reactivex.*;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CreditLineService creditLineService;

    @PostMapping(value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Maybe<ResponseEntity<CheckCustomerResponse>> checkCustomer(
            @RequestBody CheckCustomerRequest checkCustomerRequest
            ) {
        log.info("Starting CustomerController.checkCustomer");
        return this.customerService.login(checkCustomerRequest);
    }

    @GetMapping(value = "/credit-line/{dni}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_STREAM_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Single<CreditLineResponse> findCreditLineByDni(@PathVariable(value = "dni") String dni) {
        log.info("Starting CustomerController.findCreditLineByDni");
        return this.creditLineService.getCreditLine(dni);
    }




}
