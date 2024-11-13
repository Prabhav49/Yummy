package com.prabhav.yummy.controller;

import com.prabhav.yummy.dto.CustomerRequest;
import com.prabhav.yummy.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.deleteCustomerByEmail(request.email()));
    }
}
