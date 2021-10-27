package edu.cuongnghiem.springschoolmanager.controller.admin_controllers;

import edu.cuongnghiem.springschoolmanager.command.ContactCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.converters.ContactConverter;
import edu.cuongnghiem.springschoolmanager.converters.StudentConverter;
import edu.cuongnghiem.springschoolmanager.entity.Student;
import edu.cuongnghiem.springschoolmanager.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cuongnghiem on 27/10/2021
 **/
@Controller
@RequestMapping("/admin/student")
public class AdminStudentController {

    private final StudentService studentService;
    private final StudentConverter studentConverter;
    private final ContactConverter contactConverter;

    public AdminStudentController(StudentService studentService, StudentConverter studentConverter, ContactConverter contactConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
        this.contactConverter = contactConverter;
    }

    @GetMapping("/new")
    public String getNewView(Model model) {
        model.addAttribute("student",
                StudentCommand.builder().contactCommand(new ContactCommand()).build());
        return "/admin/student/new";
    }

    @PostMapping("/new")
    public String addNewStudent(@Valid @ModelAttribute("student") StudentCommand studentCommand,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/student/new";
        }

        studentService.save(studentConverter.commandToEntity(studentCommand));
        return "redirect:/admin/student/new";
    }

    @GetMapping("/edit")
    public String getStudentList(Model model,
                                 @RequestParam(name = "name", defaultValue = "") String name,
                                 @RequestParam(name = "phone", defaultValue = "") String phone,
                                 @RequestParam(name = "page", defaultValue = "1") String page) {
        List<StudentCommand> students;

        if (phone.trim().equals("")) {
            students = studentService.findStudentCommandByName(name);
        } else if (name.trim().equals("")) {
            students = studentService.findStudentCommandByPhone(phone);
        } else {
            students = studentService.findStudentCommandByNameAndPhone(name, phone);
        }

        Page<StudentCommand> studentPage = studentService.convertToPage(students, Integer.parseInt(page), 10);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("totalPage", studentPage.getTotalPages());
        model.addAttribute("studentsFound", studentPage.getTotalElements());
        model.addAttribute("page", Long.valueOf(page));

        return "/admin/student/updateFind";
    }

    @GetMapping("/{id}/edit")
    public String getEditStudentView(Model model,
                                     @PathVariable String id) {

        model.addAttribute("student", studentService.findStudentCommandById(Long.valueOf(id)));
        return "/admin/student/update";
    }

    @PostMapping("/{id}/edit")
    public String updateStudent(@Valid @ModelAttribute("student") StudentCommand studentCommand,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/admin/student/update";
        }

        Student student = studentService.findStudentById(studentCommand.getId());
        student.setFirstName(studentCommand.getFirstName());
        student.setLastName(studentCommand.getLastName());
        student.setContact(contactConverter.commandToEntity(studentCommand.getContactCommand()));
        studentService.save(student);

        return "redirect:/admin/student/edit";
    }
}
