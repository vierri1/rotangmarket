package com.market.rotang.rotangmarket.controller.admin;

import com.market.rotang.rotangmarket.entity.Category;
import com.market.rotang.rotangmarket.service.CategoryService;
import com.market.rotang.rotangmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/category/")
public class CategoryAdminController {

    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public CategoryAdminController(CategoryService categoryService,
                                   ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("all")
    public String main(Model model, @ModelAttribute("msg") String msg) {
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("msg", msg);
        return "admin/categories";
    }

    @GetMapping(value = "edit/{id}")
    public String edit(@PathVariable Long id,
                       @RequestParam(required = false) String newImage,
                       Model model) {
        Category category = categoryService.get(id);

        if (newImage != null) {
            category.setImage(newImage);
        }

        model.addAttribute("category", category);
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
                       RedirectAttributes redirectAttributes) {
        Category category = categoryService.add(name, description);
        redirectAttributes.addAttribute("msg",
                String.format("Категория %s создана с id %d", name, category.getId()));
        return "redirect:/admin/category/all";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Long productCount = productService.getCountByCategory(id);

        if (productCount > 0) {
            redirectAttributes.addAttribute("msg",
                    String.format("Категория %d не удалена, так как в ней содержаться продукты. " +
                            "Перед удалением категории необходимо удалить все ее продукты", id));
        } else {
            categoryService.delete(id);
            redirectAttributes.addAttribute("msg",
                    String.format("Категория с id %d удалена", id));
        }

        return "redirect:/admin/category/all";
    }

    @GetMapping("image/set")
    public String setCategoryImage(@RequestParam Long categoryId,
                                   @RequestParam String img,
                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("newImage", img);
        return "redirect:/admin/category/edit/" + categoryId;
    }
}
