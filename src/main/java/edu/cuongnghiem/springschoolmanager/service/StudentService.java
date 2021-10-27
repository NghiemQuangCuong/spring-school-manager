package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.command.MarkCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.entity.ExamType;
import edu.cuongnghiem.springschoolmanager.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface StudentService {
    List<Student> findAll();
    List<StudentCommand> findStudentCommandByName(String name);
    List<StudentCommand> findStudentCommandByPhone(String phone);
    List<StudentCommand> findStudentCommandByNameAndPhone(String name, String phone);
    StudentCommand findStudentCommandById(Long id);
    Student findStudentById(Long id);
    Student save(Student student);
    Map<String, Map<ExamType, MarkCommand>> getMarkMapSubjectKey(Long id);
    Page<StudentCommand> convertToPage(List<StudentCommand> studentCommandList, int page, int recordPerPage);
    void deleteById(Long id);
}
