package com.prabhav.yummy.auth;

import com.prabhav.yummy.entity.Customer;
import com.prabhav.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepo customerRepo;

    public String login(String email, String password) {
        Customer customer = customerRepo.findByEmail(email);

        if (customer != null && customer.getPassword().equals(password)) {
            return "User logged in successfully.";
        } else {
            return "Invalid email or password.";
        }
    }
}
