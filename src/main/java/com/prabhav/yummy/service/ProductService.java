package com.prabhav.yummy.service;

import com.prabhav.yummy.dto.ProductUpdateRequest;
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

    public String updateProduct(ProductUpdateRequest request, Long productId) {
        Product existingProduct = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        StringBuilder updatedFields = new StringBuilder("Updated Product id = " + productId + ", ");

        if (request.getName() != null && !request.getName().equals(existingProduct.getName())) {
            existingProduct.setName(request.getName());
            updatedFields.append("name = ").append(request.getName()).append(", ");
        }
        if (request.getPrice() != null && !request.getPrice().equals(existingProduct.getPrice())) {
            existingProduct.setPrice(request.getPrice());
            updatedFields.append("price = ").append(request.getPrice()).append(", ");
        }

        productRepo.save(existingProduct);

        if (updatedFields.toString().endsWith(", ")) {
            updatedFields.setLength(updatedFields.length() - 2);
        }

        return updatedFields.toString();
    }


    public boolean deleteProductById(Long id) {
        if (productRepo.existsById(id)) {
            productRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Product> getTop2ProductsInRange() {
        return productRepo.findTop2ProductsInRange();
    }
}
