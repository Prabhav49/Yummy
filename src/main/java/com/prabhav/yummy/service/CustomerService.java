package com.prabhav.yummy.service;

import com.prabhav.yummy.dto.UpdateCustomerRequest;
import com.prabhav.yummy.entity.Customer;
import com.prabhav.yummy.dto.CustomerRequest;
import com.prabhav.yummy.mapper.CustomerMapper;
import com.prabhav.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        repo.save(customer);
        return "Created";
    }

    public String updateCustomer(UpdateCustomerRequest request, String email) {
        Customer customer = repo.findByEmail(email);

        if (customer == null) {
            return "Customer Not Found";
        }

        StringBuilder updatedFields = new StringBuilder();


        if (request.getFirstName() != null) {
            customer.setFirstName(request.getFirstName());
            updatedFields.append("Name, ");
        }
        if (request.getLastName() != null) {
            customer.setLastName(request.getLastName());
            updatedFields.append("Last Name, ");
        }
        if (request.getAddress() != null) {
            customer.setAddress(request.getAddress());
            updatedFields.append("Address, ");
        }
        if (request.getCity() != null) {
            customer.setCity(request.getCity());
            updatedFields.append("City, ");
        }
        if (request.getPincode() != null) {
            customer.setPincode(Integer.parseInt(request.getPincode()));
            updatedFields.append("Pincode, ");
        }
        if (request.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(request.getPassword()));
            updatedFields.append("Password, ");
        }

        if (updatedFields.length() > 0) {
            updatedFields.setLength(updatedFields.length() - 2);
        }

        repo.save(customer);

        if (updatedFields.length() > 0) {
            return "Customer " + updatedFields.toString() + " updated successfully";
        } else {
            return "No fields were updated";
        }
    }

    public String deleteCustomerByEmail(String email) {
        Customer customer = repo.findByEmail(email);
        if (customer != null) {
            repo.delete(customer);
            return "Customer deleted successfully";
        } else {
            return "Customer Not Found";
        }
    }
}
