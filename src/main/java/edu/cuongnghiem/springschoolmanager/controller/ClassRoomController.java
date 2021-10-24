package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.analyzer.ClassRoomAnalyzer;
import edu.cuongnghiem.springschoolmanager.analyzer.charts.BarChart;
import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.exception.BadRequestException;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Slf4j
@Controller
@RequestMapping("/class")
public class ClassRoomController {

    private final ClassTypeService classTypeService;
    private final ClassRoomService classRoomService;
    private final ClassRoomAnalyzer classRoomAnalyzer;

    public ClassRoomController(ClassTypeService classTypeService, ClassRoomService classRoomService, ClassRoomAnalyzer classRoomAnalyzer) {
        this.classTypeService = classTypeService;
        this.classRoomService = classRoomService;
        this.classRoomAnalyzer = classRoomAnalyzer;
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
                             @RequestParam(name = "recordPerPage", defaultValue = "5") String recordPerPage,
                             @RequestParam(name = "name", defaultValue = "") String name,
                             @RequestParam(name = "analyze1", defaultValue = "all") String analyze1,
                             @RequestParam(name = "analyzeSection", defaultValue = "") String analyzeSection) {
        // Prepare for students
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

        // Prepare for teachers
        model.addAttribute("teachers", classRoomCommand.getTeacherCommands());

        // prepare for analyze
        model.addAttribute("classRooms", classRoomService.getAllClassRoom());
        model.addAttribute("analyze1", analyze1);
        StringBuilder updateStatus1 = new StringBuilder();
        BarChart barChart1 = new BarChart();
        if (analyze1.equals("all"))
            barChart1 = classRoomAnalyzer.students(classId, null, updateStatus1);
        else
            barChart1 = classRoomAnalyzer.students(classId, Long.valueOf(analyze1), updateStatus1);
        model.addAttribute("updateStatus1", updateStatus1.toString());
        model.addAttribute("xValue1", barChart1.getXValues().get(0));
        model.addAttribute("xValue2", barChart1.getXValues().get(1));
        model.addAttribute("yValue1", barChart1.getYValues().get(0));
        model.addAttribute("yValue2", barChart1.getYValues().get(1));

        return "/class/details";
    }
}
