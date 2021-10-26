package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cuongnghiem on 26/10/2021
 **/
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public String getIndex() {
        return "/teacher/index";
    }

    @PostMapping("")
    public String getTeachers(Model model,
                              @RequestParam String name,
                              @RequestParam String phone) {
        if (name.trim().equals("") && phone.trim().equals("")) {
            model.addAttribute("notFound", "notFound");
            return "/teacher/index";
        }
        if (phone.trim().equals("")) {
            List<TeacherCommand> teachers = teacherService.findTeacherCommandByName(name.trim());
            if (teachers.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("teachers", teachers);
        }
        else if (name.trim().equals("")) {
            List<TeacherCommand> teachers = teacherService.findTeacherCommandByPhone(phone.trim());
            if (teachers.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("teachers", teachers);
        } else {
            List<TeacherCommand> teachers = teacherService.findTeacherCommandByNameAndPhone(name.trim(), phone.trim());
            if (teachers.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("teachers", teachers);
        }
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        return "/teacher/index";
    }

    @GetMapping("/{id}")
    public String getDetails(Model model,
                             @PathVariable String id) {
        Long teacherId = Long.valueOf(id);
        model.addAttribute("teacher", teacherService.findTeacherCommandById(teacherId));
        model.addAttribute("classes", teacherService.findClassRoomTeachByThisTeacher(teacherId));
        return "/teacher/details";
    }
}
