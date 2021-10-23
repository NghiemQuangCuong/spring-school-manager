package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Controller
@RequestMapping("/class")
public class ClassRoomController {

    private final ClassTypeService classTypeService;
    private final ClassRoomService classRoomService;

    public ClassRoomController(ClassTypeService classTypeService, ClassRoomService classRoomService) {
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
        model.addAttribute("classes", classRoomService.getClassRoomCommand());
        return "/class/index";
    }

    @PostMapping("")
    public String getIndexFilter(Model model,
                                 @RequestParam("filter") String filter,
                                 @RequestParam("name") String name) {
        model.addAttribute("filter", filter);
        model.addAttribute("name", name);
        if (filter.equals("All"))
            model.addAttribute("classes",
                    classRoomService.getClassRoomCommandByClassTypeNameAndByName(null, name));
        else
            model.addAttribute("classes",
                    classRoomService.getClassRoomCommandByClassTypeNameAndByName(filter, name));
        return "/class/index";
    }
}
