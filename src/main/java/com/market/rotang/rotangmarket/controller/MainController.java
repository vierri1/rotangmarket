package com.market.rotang.rotangmarket.controller;

import com.market.rotang.rotangmarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private CategoryService categoryService;

    @Autowired
    public MainController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "index";
    }

    @GetMapping("/shop")
    public String shop() {
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
