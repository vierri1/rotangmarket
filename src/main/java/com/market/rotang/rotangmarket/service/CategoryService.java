package com.market.rotang.rotangmarket.service;

import com.market.rotang.rotangmarket.entity.Category;
import com.market.rotang.rotangmarket.exception.CategoryNotFoundException;
import com.market.rotang.rotangmarket.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private String defaultImage;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           @Value("${img.default}") String defaultImage) {
        this.categoryRepository = categoryRepository;
        this.defaultImage = defaultImage;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Category get(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category save(Category category) {
        if (category.getImage() == null) {
            category.setImage(defaultImage);
        }

        return categoryRepository.save(category);
    }

    public Category add(String name, String description) {
        return save(Category.builder()
                .name(name)
                .description(description)
                .build());
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
