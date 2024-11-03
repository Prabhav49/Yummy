package com.prabhav.yummy.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prabhav.yummy.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
