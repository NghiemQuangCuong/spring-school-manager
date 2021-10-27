package edu.cuongnghiem.springschoolmanager.controller.admin_controllers;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.ClassTypeCommand;
import edu.cuongnghiem.springschoolmanager.converters.ClassRoomConverter;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;

/**
 * Created by cuongnghiem on 27/10/2021
 **/

@Controller
@RequestMapping("/admin/class")
public class AdminClassRoomController {

    private final ClassRoomService classRoomService;
    private final ClassTypeService classTypeService;
    private final ClassRoomConverter classRoomConverter;

    public AdminClassRoomController(ClassRoomService classRoomService, ClassTypeService classTypeService, ClassRoomConverter classRoomConverter) {
        this.classRoomService = classRoomService;
        this.classTypeService = classTypeService;
        this.classRoomConverter = classRoomConverter;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ClassTypeCommand.class, "classTypeCommand", new PropertyEditorSupport() {
            @Override
            public void setAsText(String classTypeId) {
                setValue(classTypeService.findCommandById(Long.valueOf(classTypeId)));
            }
        });
    }

    @GetMapping("/new")
    public String getNewClassRoom(Model model) {
        model.addAttribute("classRoom", new ClassRoomCommand());
        model.addAttribute("classTypes", classTypeService.findAllCommand());
        return "/admin/classroom/newOrUpdate";
    }

    @PostMapping("/new")
    public String addNewClassRoom(@Valid @ModelAttribute("classRoom") ClassRoomCommand classRoom,
                                  BindingResult bindingResult, Model model
    ) {
        model.addAttribute("classTypes", classTypeService.findAllCommand());
        if (bindingResult.hasErrors()) {
            return "/admin/classroom/newOrUpdate";
        } else
        if (!classRoomService.isUniqueClassRoomName(classRoom.getName())) {
            bindingResult.rejectValue("name", "errorNameIsOccupied", "This name is occupied");
            return "/admin/classroom/newOrUpdate";
        }

        classRoomService.saveClassRoom(classRoomConverter.commandToEntity(classRoom));
        return "redirect:/admin/class/new";
    }
}
