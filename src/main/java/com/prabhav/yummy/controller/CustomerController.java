package com.prabhav.yummy.controller;

import com.prabhav.yummy.auth.JwtUtil;
import com.prabhav.yummy.dto.CustomerRequest;
import com.prabhav.yummy.dto.DeleteCustomerRequest;
import com.prabhav.yummy.dto.UpdateCustomerRequest;
import com.prabhav.yummy.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request,
            HttpServletRequest httpRequest) {
        if (!jwtUtil.validateToken(getTokenFromRequest(httpRequest))) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid or missing token");
        }
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable String email,
            @RequestBody @Valid UpdateCustomerRequest request,
            HttpServletRequest httpRequest) {
        if (!jwtUtil.validateToken(getTokenFromRequest(httpRequest))) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid or missing token");
        }
        String response = customerService.updateCustomer(request, email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(
            @RequestBody @Valid DeleteCustomerRequest request,
            HttpServletRequest httpRequest) {
        if (!jwtUtil.validateToken(getTokenFromRequest(httpRequest))) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid or missing token");
        }
        return ResponseEntity.ok(customerService.deleteCustomerByEmail(request.getEmail()));
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Authorization"); // Extract token from Authorization header
    }
}
