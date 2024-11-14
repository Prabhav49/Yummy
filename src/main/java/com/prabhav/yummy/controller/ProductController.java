package com.prabhav.yummy.controller;


import com.prabhav.yummy.entity.Product;
import com.prabhav.yummy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/top2")
    public ResponseEntity<List<Product>> getTop2ProductsInRange() {
        List<Product> products = productService.getTop2ProductsInRange();
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProductById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
