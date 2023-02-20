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

    @Override
    public CustomerDto getCustomer(String id) {
        return customerRepository.findById(id).map(CustomerConverter::toDto).orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDto updateCustomer(String id, CustomerAddRequest customerAddRequest) {
        return CustomerConverter.toDto(
                customerRepository.findById(id)
                        .map(customerEntity -> {
                            customerEntity.setFullName(customerAddRequest.getFullName());
                            customerEntity.setPassword(customerAddRequest.getPassword());
                            customerEntity.setPhone(customerAddRequest.getPhone());
                            customerEntity.setEmail(customerAddRequest.getEmail());
                            customerEntity.setAddress(customerAddRequest.getAddresses());
                            customerEntity.setCardNumbers(customerAddRequest.getCardNumbers());
                            return customerEntity;
                        })
                        .map(customerRepository::save)
                        .orElseThrow(RuntimeException::new));
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
