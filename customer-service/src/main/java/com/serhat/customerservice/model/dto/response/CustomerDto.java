package com.serhat.customerservice.model.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDto {

    private String id;
    private String fullName;

    private String email;

    private String password;

    private String phone;

    private List<String> address;

    private List<String> cardNumbers;
}
