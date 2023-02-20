package com.serhat.customerservice.model.converter;

import com.serhat.customerservice.model.dto.request.CustomerAddRequest;
import com.serhat.customerservice.model.dto.response.CustomerDto;
import com.serhat.customerservice.model.entity.CustomerEntity;

public class CustomerConverter {
    public static CustomerEntity toEntity(CustomerAddRequest customerAddRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFullName(customerAddRequest.getFullName());
        customerEntity.setEmail(customerAddRequest.getEmail());
        customerEntity.setPassword(customerAddRequest.getPassword());
        customerEntity.setPhone(customerAddRequest.getPhone());
        customerEntity.setAddress(customerAddRequest.getAddress());
        customerEntity.setCardNumbers(customerAddRequest.getCardNumbers());
        return customerEntity;
    }

    public static CustomerDto toDto(CustomerEntity customerEntity) {
        return CustomerDto.builder()
                .id(customerEntity.getId())
                .fullName(customerEntity.getFullName())
                .email(customerEntity.getEmail())
                .password(customerEntity.getPassword())
                .phone(customerEntity.getPhone())
                .address(customerEntity.getAddress())
                .cardNumbers(customerEntity.getCardNumbers())
                .build();
    }
}
