package com.prabhav.yummy.service;

import com.prabhav.yummy.entity.Product;
import com.prabhav.yummy.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    public List<Product> getTop2ProductsInRange() {
        return productRepo.findTop2ProductsInRange();
    }
}
