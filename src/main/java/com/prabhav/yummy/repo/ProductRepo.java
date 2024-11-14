package com.prabhav.yummy.repo;

import com.prabhav.yummy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN 15 AND 30 ORDER BY p.price ASC ")
    List<Product> findTop2ProductsInRange();
}
