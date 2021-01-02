package com.market.rotang.rotangmarket.controller;

import com.market.rotang.rotangmarket.entity.Category;
import com.market.rotang.rotangmarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private CategoryService categoryService;

    @Autowired
    public AdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String main(Model model, @ModelAttribute("msg") String msg) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("msg", msg);
        return "admin/admin";
    }

    @GetMapping("category/edit/{id}")
    public String categoryEdit(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.get(id));
        model.addAttribute("action", "edit");
        return "admin/category-edit";
    }

    @PostMapping("category/edit")
    public String updateCategory(Category category,
                                 RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addAttribute("msg",
                String.format("Категория %s c id %d обновлена", category.getName(), category.getId()));
        return "redirect:/admin";
    }

    @GetMapping("category/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "add");
        return "admin/category-edit";
    }

    @PostMapping("category/add")
    public String save(@RequestParam String name,
                       @RequestParam String description,
                       @RequestParam String image,
                       RedirectAttributes redirectAttributes) {
        Category category = categoryService.add(name, description, image);
        redirectAttributes.addAttribute("msg",
                String.format("Категория %s создана с id %d", name, category.getId()));
        return "redirect:/admin";
    }

    @GetMapping("category/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        redirectAttributes.addAttribute("msg",
                String.format("Категория с id %d удалена", id));
        return "redirect:/admin";
    }
}
