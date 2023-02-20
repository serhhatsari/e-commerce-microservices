package com.serhat.customerservice.model.converter;

import com.serhat.customerservice.model.dto.request.CustomerAddRequest;
import com.serhat.customerservice.model.dto.response.CustomerDto;
import com.serhat.customerservice.model.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerConverter {
    public static CustomerEntity toEntity(CustomerAddRequest customerAddRequest) {
        log.info("Converting customerAddRequest to customerEntity: {}", customerAddRequest);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFullName(customerAddRequest.getFullName());
        customerEntity.setEmail(customerAddRequest.getEmail());
        customerEntity.setPassword(customerAddRequest.getPassword());
        customerEntity.setPhone(customerAddRequest.getPhone());
        customerEntity.setAddress(customerAddRequest.getAddresses());
        customerEntity.setCardNumbers(customerAddRequest.getCardNumbers());
        return customerEntity;
    }

    public static CustomerDto toDto(CustomerEntity customerEntity) {
        log.info("Converting customerEntity to customerDto: {}", customerEntity);
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
