package com.hackaton.business.backend.business.impl;

import com.hackaton.business.backend.expose.customerDTO.CheckCustomerRequest;
import com.hackaton.business.backend.expose.customerDTO.CheckCustomerResponse;
import com.hackaton.business.backend.repository.CustomerRepository;
import io.reactivex.Maybe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.hackaton.business.backend.business.CustomerService;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public Maybe<ResponseEntity<CheckCustomerResponse>> checkCustomer(CheckCustomerRequest checkCustomerRequest) {
        return Maybe
                .fromCallable(() ->
                customerRepository.findCustomerByDniAndPassword(checkCustomerRequest.getDni(), checkCustomerRequest.getPassword()))
                .map(c -> {
                    if(c.isEmpty()){
                        return new ResponseEntity<>(new CheckCustomerResponse(),
                                HttpStatus.NO_CONTENT);
                    }

                    CheckCustomerResponse checkCustomerResponse = this.modelMapper.map(c.get(), CheckCustomerResponse.class);
                    return new ResponseEntity<>(checkCustomerResponse,
                            HttpStatus.OK);
                })
                .doOnSuccess(s -> log.info("Finished CustomerImpl.login"))
                .doOnError(ex -> log.error("Error on CustomerImpl.login"))
                .onErrorResumeNext(ex -> {
                    log.error("Error");
                    return Maybe.error(ex);
                });
    }
}
