package com.market.rotang.rotangmarket.repository;

import com.market.rotang.rotangmarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
