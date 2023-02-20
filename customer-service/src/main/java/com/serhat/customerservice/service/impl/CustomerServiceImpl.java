package com.serhat.customerservice.service.impl;

import com.serhat.customerservice.model.converter.CustomerConverter;
import com.serhat.customerservice.model.dto.request.CustomerAddRequest;
import com.serhat.customerservice.model.dto.response.CustomerDto;
import com.serhat.customerservice.repository.CustomerRepository;
import com.serhat.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public CustomerDto createCustomer(CustomerAddRequest customerAddRequest) {
        return CustomerConverter.toDto(customerRepository.save(CustomerConverter.toEntity(customerAddRequest)));
    }
}
