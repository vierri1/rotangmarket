package com.market.rotang.rotangmarket.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class MainAdminController {

    @GetMapping
    public String main() {
        return "admin/admin";
    }
}
