package com.market.rotang.rotangmarket.controller.admin;

import com.market.rotang.rotangmarket.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/image/")
public class ImageAdminController {

    private ImageService imageService;

    @Autowired
    public ImageAdminController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("all")
    public String getAll(Model model,
                         @RequestParam(required = false) String msg) {
        model.addAttribute("imgNames", imageService.getNames());
        model.addAttribute("msg", msg);
        return "admin/images";
    }

    @PostMapping("add")
    public String add(@RequestParam MultipartFile image,
                      RedirectAttributes redirectAttributes) {
        boolean added = imageService.add(image);

        String msg = added
                ? String.format("Изображение %s добавлено", image.getOriginalFilename())
                : String.format("Ошибка загрузки изображения %s", image.getOriginalFilename());

        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/admin/image/all";
    }

    @GetMapping("delete/{imgName:.+}")
    public String delete(@PathVariable String imgName,
                         RedirectAttributes redirectAttributes) {
        boolean deleted = imageService.remove(imgName);

        String msg = deleted
                ? String.format("Изображение %s удалено", imgName)
                : String.format("Ошибка удаления изображения %s", imgName);

        redirectAttributes.addAttribute("msg", msg);
        return "redirect:/admin/image/all";
    }

    @GetMapping("category/{categoryId}")
    public String setCategoryImage(@PathVariable String categoryId, Model model) {
        model.addAttribute("imgNames", imageService.getNames());
        model.addAttribute("categoryId", categoryId);
        return "admin/set-image";
    }

}
