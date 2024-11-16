package com.prabhav.yummy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerRequest {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String pincode;
    private String password;
}