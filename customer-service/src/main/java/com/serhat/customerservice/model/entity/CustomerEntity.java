package com.serhat.customerservice.model.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(value = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerEntity {
    @Id
    private String id;

    @Field(name = "full_name")
    @NotBlank
    private String fullName;

    @Field(name = "email")
    @Email
    private String email;

    @Field(name = "password")
    private String password;

    @Field(name = "phone")
    private String phone;

    @Field(name = "addresses")
    private List<String> address;

    @Field(name = "card_numbers")
    private List<String> cardNumbers;
}
