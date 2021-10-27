package edu.cuongnghiem.springschoolmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cuongnghiem on 26/10/2021
 **/

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String getIndex() {
        return "/admin/index";
    }
}
