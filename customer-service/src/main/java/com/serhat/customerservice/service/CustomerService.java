package com.serhat.customerservice.service;

import com.serhat.customerservice.model.dto.request.CustomerAddRequest;
import com.serhat.customerservice.model.dto.response.CustomerDto;
import com.serhat.customerservice.model.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerAddRequest customerAddRequest);
}
