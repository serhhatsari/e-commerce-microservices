package com.serhat.customerservice.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerAddRequest {
    private String fullName;

    private String email;

    private String password;

    private String phone;

    private List<String> addresses;

    private List<String> cardNumbers;
}
