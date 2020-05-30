package com.market.rotang.rotangmarket.service;

import com.market.rotang.rotangmarket.entity.Product;
import com.market.rotang.rotangmarket.exception.ProductNotFoundException;
import com.market.rotang.rotangmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepository.getAllByCategoryId(categoryId);
    }
}
