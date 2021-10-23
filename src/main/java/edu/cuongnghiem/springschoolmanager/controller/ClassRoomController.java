package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.exception.BadRequestException;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.springframework.data.domain.Page;
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

    @GetMapping("")
    public String getIndex(Model model) {
        model.addAttribute("classTypes", classTypeService.getAllName());
        model.addAttribute("filter", "All");
        model.addAttribute("classes", classRoomService.getClassRoomCommand());
        return "/class/index";
    }

    @PostMapping("")
    public String getIndexFilter(Model model,
                                 @RequestParam("filter") String filter,
                                 @RequestParam("name") String name) {
        model.addAttribute("classTypes", classTypeService.getAllName());
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

    @GetMapping("/{id}")
    public String getDetails(Model model,
                             @PathVariable String id,
                             @RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
                             @RequestParam(name = "recordPerPage", defaultValue = "10") String recordPerPage,
                             @RequestParam(name = "name", defaultValue = "") String name) {
        Long classId = Long.valueOf(id);
        int curPage = Integer.parseInt(currentPage);
        int recPerPage = Integer.parseInt(recordPerPage);
        ClassRoomCommand classRoomCommand = classRoomService.getClassRoomCommandById(classId);
        if (classRoomCommand == null)
            throw new NotFoundException("Cannot find classroom, id = " + id);
        Page<StudentCommand> studentCommandPage =
                classRoomService.getStudentsCommandPagingFromClassRoomIdAndName(classId, curPage, recPerPage, name);
        if (studentCommandPage == null)
            throw new BadRequestException("Current page exceed max page");
        model.addAttribute("class", classRoomCommand);
        model.addAttribute("students",
                studentCommandPage.getContent());
        model.addAttribute("page", curPage);
        model.addAttribute("totalPage", studentCommandPage.getTotalPages());
        model.addAttribute("totalStudent", studentCommandPage.getTotalElements());
        model.addAttribute("recordPerPage", recPerPage);
        model.addAttribute("name", name);
        return "/class/details";
    }
}
