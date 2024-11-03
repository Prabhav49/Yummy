package com.prabhav.yummy.auth;

import com.prabhav.yummy.entity.Customer;
import com.prabhav.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final CustomerRepo customerRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String email, String password) {
        Customer customer = customerRepo.findByEmail(email);

        if (customer != null && passwordEncoder.matches(password, customer.getPassword())) {
            return jwtUtil.generateToken(email);
        } else {
            return "Invalid email or password";
        }
    }
}
