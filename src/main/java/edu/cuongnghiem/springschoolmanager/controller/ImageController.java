package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.entity.Student;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ImageService;
import edu.cuongnghiem.springschoolmanager.service.StudentService;
import edu.cuongnghiem.springschoolmanager.service.TeacherService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Controller
@RequestMapping("/image")
public class ImageController {

    private final ClassRoomService classRoomService;
    private final ImageService imageService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public ImageController(ClassRoomService classRoomService, ImageService imageService, TeacherService teacherService, StudentService studentService) {
        this.classRoomService = classRoomService;
        this.imageService = imageService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("/class/{id}")
    public void getClassCoverImage(@PathVariable String id,
                                   HttpServletResponse response) throws IOException {
        ClassRoom classRoom = classRoomService.getClassRoomById(Long.valueOf(id));
        if (classRoom == null)
            throw new NotFoundException("ClassRoom not found, id = " + id);
        byte[] image = classRoom.getImage();
        if (image == null) {
            // we set classroom image to random if it is null
            image = imageService.getRandomClassImageCover();
            classRoom.setImage(image);
            classRoomService.saveClassRoom(classRoom);
        }
        response.setContentType("image/*");
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
        response.getOutputStream().close();
    }

    @GetMapping("/teacher/{id}")
    public void getTeacherImage(HttpServletResponse response,
                                @PathVariable String id) throws IOException {
        Long teacherId = Long.valueOf(id);
        Teacher teacher = teacherService.getTeacherById(teacherId);
        if (teacher == null)
            throw new NotFoundException("Cannot find teacher, id = " + teacherId);
        byte[] image = teacher.getImage();
        if (image == null) {
            image = imageService.getRandomTeacherImage();
            teacher.setImage(image);
            teacherService.save(teacher);
        }
        response.setContentType("image/*");
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
        response.getOutputStream().close();
    }

    @GetMapping("/student/{id}")
    public void getStudentImage(HttpServletResponse response,
                                @PathVariable String id) throws IOException {
        Student student = studentService.findStudentById(Long.valueOf(id));
        if (student == null)
            throw new NotFoundException("Student not found, id = " + id);
        byte[] image = student.getImage();
        if (image == null) {
            image = imageService.getRandomStudentImage();
            student.setImage(image);
            studentService.save(student);
        }
        response.setContentType("image/*");
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
        response.getOutputStream().close();
    }
}
