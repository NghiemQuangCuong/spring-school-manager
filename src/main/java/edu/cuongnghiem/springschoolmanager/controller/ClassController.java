package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Controller
@RequestMapping("/class")
public class ClassController {

    private final ClassTypeService classTypeService;
    private final ClassRoomService classRoomService;

    public ClassController(ClassTypeService classTypeService, ClassRoomService classRoomService) {
        this.classTypeService = classTypeService;
        this.classRoomService = classRoomService;
    }

    @ModelAttribute
    public void addClassType(Model model) {
        model.addAttribute("classTypes", classTypeService.getAllName());
    }

    @GetMapping("")
    public String getIndex(Model model) {
        model.addAttribute("filter", "All");
        // class name
        // class type
        // numbers of teacher
        // numbers of student
        model.addAttribute("classes", classRoomService.getClassRoomCommand());
        return "/class/index";
    }
}
