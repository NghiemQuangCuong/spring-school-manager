package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.command.MarkCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.entity.ExamType;
import edu.cuongnghiem.springschoolmanager.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            List<StudentCommand> lists = studentService.findStudentCommandByName(name.trim());
            if (lists.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("students", lists);
        }
        else if (name.trim().equals("")) {
            List<StudentCommand> lists = studentService.findStudentCommandByPhone(phone.trim());
            if (lists.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("students", lists);
        } else {
            List<StudentCommand> lists = studentService.findStudentCommandByNameAndPhone(name.trim(), phone.trim());
            if (lists.isEmpty())
                model.addAttribute("notFound", "notFound");
            model.addAttribute("students", lists);
        }
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        return "/student/index";
    }

    @GetMapping("/{id}")
    public String getDetails(Model model,
                             @PathVariable String id) {
        Long studentId = Long.valueOf(id);
        StudentCommand studentCommand = studentService.findStudentCommandById(studentId);
        model.addAttribute("student", studentCommand);

        Map<String, Map<ExamType, MarkCommand>> markMap =
                studentService.getMarkMapSubjectKey(studentId);
        List<String> subjectName = new ArrayList<>(markMap.keySet())
                .stream().sorted(String::compareTo).collect(Collectors.toList());
        model.addAttribute("subjectNames", subjectName);
        model.addAttribute("examTypesSemester1",
                Arrays.stream(ExamType.values()).filter(examType -> examType.getSemester().equals("HK1"))
                        .collect(Collectors.toList()));
        model.addAttribute("examTypesSemester2",
                Arrays.stream(ExamType.values()).filter(examType -> examType.getSemester().equals("HK2"))
                        .collect(Collectors.toList()));
        model.addAttribute("markMap", markMap);
        return "/student/details";
    }
}
