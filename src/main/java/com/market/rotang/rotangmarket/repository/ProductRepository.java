package com.market.rotang.rotangmarket.repository;

import com.market.rotang.rotangmarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByCategoryId(Long categoryId);

    Long countByCategoryId(Long categoryId);
}
