package com.prabhav.yummy.controller;

import com.prabhav.yummy.dto.CustomerRequest;
import com.prabhav.yummy.service.CustomerService;
import com.prabhav.yummy.auth.JwtUtil;
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

    @PostMapping
    public ResponseEntity<String> createCustoemr(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestHeader("Authorization") String token,
                                                 @RequestBody @Valid CustomerRequest request) {
        if (!jwtUtil.isTokenValid(token, request.email())) {
            return ResponseEntity.status(401).body("Invalid JWT token");
        }

        // Call update service excluding email and password
        return ResponseEntity.ok(customerService.updateCustomer(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestHeader("Authorization") String token,
                                                 @RequestBody @Valid CustomerRequest request) {
        // Remove Bearer prefix and trim the token
        String trimmedToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token.trim();

        if (!jwtUtil.isTokenValid(trimmedToken, request.email())) {
            return ResponseEntity.status(401).body("Invalid or expired JWT token");
        }

        // Extract email from the token to match with the provided email
        String emailFromToken = jwtUtil.extractEmail(trimmedToken);

        // Now we can use emailFromToken for validation or deletion logic
        customerService.deleteCustomerByEmail(emailFromToken);

        return ResponseEntity.ok("Account deleted successfully");
    }
}
