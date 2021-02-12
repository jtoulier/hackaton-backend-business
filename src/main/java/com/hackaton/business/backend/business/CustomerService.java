package com.hackaton.business.backend.business;

import com.hackaton.business.backend.expose.customerDTO.CheckCustomerRequest;
import com.hackaton.business.backend.expose.customerDTO.CheckCustomerResponse;
import io.reactivex.Maybe;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Maybe<ResponseEntity<CheckCustomerResponse>> checkCustomer(CheckCustomerRequest checkCustomerRequest);

}
