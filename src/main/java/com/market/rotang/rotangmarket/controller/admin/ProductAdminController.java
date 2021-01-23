package com.market.rotang.rotangmarket.controller.admin;

import com.market.rotang.rotangmarket.entity.Product;
import com.market.rotang.rotangmarket.service.CategoryService;
import com.market.rotang.rotangmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/product/")
public class ProductAdminController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ProductAdminController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public String allByCategory(@RequestParam(required = false) Long categoryId, Model model) {
        model.addAttribute("categories", categoryService.getAll());
        if (categoryId != null) {
            model.addAttribute("selectedCategory", categoryService.get(categoryId));
            model.addAttribute("products", productService.getByCategory(categoryId));
        }
        return "admin/products";
    }

    @GetMapping("/add")
    public String add(Product product) {
        return "";
    }

}
