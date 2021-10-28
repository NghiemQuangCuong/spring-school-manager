package edu.cuongnghiem.springschoolmanager.controller.admin_controllers;

import edu.cuongnghiem.springschoolmanager.command.ContactCommand;
import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.converters.ContactConverter;
import edu.cuongnghiem.springschoolmanager.converters.TeacherConverter;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import edu.cuongnghiem.springschoolmanager.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cuongnghiem on 28/10/2021
 **/

@Controller
@RequestMapping("/admin/teacher")
public class AdminTeacherController {

    private final TeacherService teacherService;
    private final TeacherConverter teacherConverter;
    private final ContactConverter contactConverter;

    public AdminTeacherController(TeacherService teacherService, TeacherConverter teacherConverter, ContactConverter contactConverter) {
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
        this.contactConverter = contactConverter;
    }

    @GetMapping("/new")
    public String getNewView(Model model) {
        model.addAttribute("teacher",
                TeacherCommand.builder().contactCommand(new ContactCommand()).build());
        return "/admin/teacher/new";
    }

    @PostMapping("/new")
    public String addNewTeacher(@Valid @ModelAttribute("teacher") TeacherCommand teacherCommand,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/admin/teacher/new";
        }

        teacherService.save(teacherConverter.commandToEntity(teacherCommand));
        return "redirect:/admin/teacher/new";

        //teachers, page, totalPage,
    }

    @GetMapping("/edit")
    public String getTeacherList(Model model,
                                 @RequestParam(name = "name", defaultValue = "") String name,
                                 @RequestParam(name = "phone", defaultValue = "") String phone,
                                 @RequestParam(name = "page", defaultValue = "1") String page) {
        List<TeacherCommand> teachers;

        if (phone.trim().equals("")) {
            teachers = teacherService.findTeacherCommandByName(name);
        } else if (name.trim().equals("")) {
            teachers = teacherService.findTeacherCommandByPhone(phone);
        } else {
            teachers = teacherService.findTeacherCommandByNameAndPhone(name, phone);
        }

        Page<TeacherCommand> teacherPage = teacherService.convertToPage(teachers, Integer.parseInt(page), 10);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("teachers", teacherPage.getContent());
        model.addAttribute("totalPage", teacherPage.getTotalPages());
        model.addAttribute("teachersFound", teacherPage.getTotalElements());
        model.addAttribute("page", Long.valueOf(page));

        return "/admin/teacher/updateFind";
    }

    @GetMapping("/{id}/edit")
    public String getEditTeacherView(Model model,
                                     @PathVariable String id) {

        model.addAttribute("teacher",
                teacherService.findTeacherCommandById(Long.valueOf(id)));
        return "/admin/teacher/update";
    }

    @PostMapping("/{id}/edit")
    public String updateTeacher(@Valid @ModelAttribute("teacher") TeacherCommand teacherCommand,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/admin/teacher/update";
        }

        Teacher teacher = teacherService.getTeacherById(teacherCommand.getId());
        teacher.setFirstName(teacherCommand.getFirstName());
        teacher.setLastName(teacherCommand.getLastName());
        teacher.setContact(contactConverter.commandToEntity(teacherCommand.getContactCommand()));
        teacherService.save(teacher);

        return "redirect:/admin/teacher/edit";
    }

    @GetMapping("/remove")
    public String getTeacherRemoveList(Model model,
                                 @RequestParam(name = "name", defaultValue = "") String name,
                                 @RequestParam(name = "phone", defaultValue = "") String phone,
                                 @RequestParam(name = "page", defaultValue = "1") String page) {
        List<TeacherCommand> teachers;

        if (phone.trim().equals("")) {
            teachers = teacherService.findTeacherCommandByName(name);
        } else if (name.trim().equals("")) {
            teachers = teacherService.findTeacherCommandByPhone(phone);
        } else {
            teachers = teacherService.findTeacherCommandByNameAndPhone(name, phone);
        }

        Page<TeacherCommand> teacherPage = teacherService.convertToPage(teachers, Integer.parseInt(page), 10);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        model.addAttribute("teachers", teacherPage.getContent());
        model.addAttribute("totalPage", teacherPage.getTotalPages());
        model.addAttribute("teachersFound", teacherPage.getTotalElements());
        model.addAttribute("page", Long.valueOf(page));

        return "/admin/teacher/remove";
    }

    @PostMapping("/{id}/remove")
    public String removeStudent(@PathVariable String id){
        Teacher teacher = teacherService.getTeacherById(Long.valueOf(id));
        if (teacher == null)
            throw new NotFoundException("Teacher not found, id = " + id);

        teacherService.deleteById(Long.valueOf(id));
        return "redirect:/admin/teacher/remove";
    }
}
