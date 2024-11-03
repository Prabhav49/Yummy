package com.prabhav.yummy.mapper;

import org.springframework.stereotype.Service;
import com.prabhav.yummy.entity.Customer;
import com.prabhav.yummy.dto.CustomerRequest;
@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .address(request.address())
                .city(request.city())
                .pincode(Integer.parseInt(request.pincode()))
                .build();
    }
}
