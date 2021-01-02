package com.market.rotang.rotangmarket.controller.admin;

import com.market.rotang.rotangmarket.entity.Category;
import com.market.rotang.rotangmarket.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/category/")
public class CategoryAdminController {

    private CategoryService categoryService;

    @Autowired
    public CategoryAdminController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    public String main(Model model, @ModelAttribute("msg") String msg) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("msg", msg);
        return "admin/categories";
    }
    @GetMapping(value = "edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.get(id));
        model.addAttribute("action", "edit");
        return "admin/category-edit";
    }

    @PostMapping("edit")
    public String update(Category category,
                         RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addAttribute("msg",
                String.format("Категория %s c id %d обновлена", category.getName(), category.getId()));
        return "redirect:/admin/category/all";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "add");
        return "admin/category-edit";
    }

    @PostMapping("add")
    public String save(@RequestParam String name,
                       @RequestParam String description,
                       @RequestParam String image,
                       RedirectAttributes redirectAttributes) {
        Category category = categoryService.add(name, description, image);
        redirectAttributes.addAttribute("msg",
                String.format("Категория %s создана с id %d", name, category.getId()));
        return "redirect:/admin/category/all";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        redirectAttributes.addAttribute("msg",
                String.format("Категория с id %d удалена", id));
        return "redirect:/admin/category/all";
    }
}
