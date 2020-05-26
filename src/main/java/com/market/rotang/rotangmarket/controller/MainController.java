package com.market.rotang.rotangmarket.controller;

import com.market.rotang.rotangmarket.service.CategoryService;
import com.market.rotang.rotangmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public MainController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(required = false) Long category,
                       Model model) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getByCategory(category));
        return "shop";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/product-details")
    public String productDetails() {
        return "product-details";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

}
