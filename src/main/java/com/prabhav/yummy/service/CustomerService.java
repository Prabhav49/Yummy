package com.prabhav.yummy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.prabhav.yummy.entity.Customer;
import com.prabhav.yummy.dto.CustomerResponse;
import com.prabhav.yummy.dto.CustomerRequest;
import com.prabhav.yummy.mapper.CustomerMapper;
import com.prabhav.yummy.repo.CustomerRepo;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}
