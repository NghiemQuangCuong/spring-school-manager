package edu.cuongnghiem.springschoolmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Controller
@RequestMapping("/class")
public class ClassController {

    @GetMapping("")
    public String getIndex() {
        return "/class/index";
    }
}
