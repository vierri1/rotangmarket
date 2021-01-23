package com.market.rotang.rotangmarket.service;

import com.market.rotang.rotangmarket.entity.Product;
import com.market.rotang.rotangmarket.entity.ProductImage;
import com.market.rotang.rotangmarket.exception.ProductNotFoundException;
import com.market.rotang.rotangmarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final String defaultImage;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          @Value("${img.default}") String defaultImage) {
        this.productRepository = productRepository;
        this.defaultImage = defaultImage;
    }

    public Product save(Product product) {
        if (CollectionUtils.isEmpty(product.getImages())) {
           product.setImages(Collections.singletonList(new ProductImage(defaultImage, -1L)));
        }

        return productRepository.save(product);
    }

    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getByCategory(Long categoryId) {
        return productRepository.getAllByCategoryId(categoryId);
    }

    public Long getCountByCategory(Long categoryId) {
        return productRepository.countByCategoryId(categoryId);
    }
}
