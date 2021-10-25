package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by cuongnghiem on 25/10/2021
 **/

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public String getIndex() {
        return "/student/index";
    }

    @PostMapping("")
    public String findStudent(Model model,
                              @RequestParam String name,
                              @RequestParam String phone) {
        if (name.trim().equals("") && phone.trim().equals("")) {
            model.addAttribute("notFound", "notFound");
            return "/student/index";
        }
        if (phone.trim().equals("")) {
            List<StudentCommand> lists = studentService.findStudentCommandByName(name);
            if (lists.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("students", lists);
        }
        else if (name.trim().equals("")) {
            List<StudentCommand> lists = studentService.findStudentCommandByPhone(phone);
            if (lists.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("students", lists);
        } else {
            List<StudentCommand> lists = studentService.findStudentCommandByNameAndPhone(name, phone);
            if (lists.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("students", lists);
        }
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        return "/student/index";
    }
}
