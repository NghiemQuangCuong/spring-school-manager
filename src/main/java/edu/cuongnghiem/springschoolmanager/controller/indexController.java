package edu.cuongnghiem.springschoolmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Controller
public class indexController {

    @GetMapping("")
    public String index() {
        return "/index";
    }
}
