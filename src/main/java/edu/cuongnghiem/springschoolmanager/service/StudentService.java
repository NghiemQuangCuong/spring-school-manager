package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.entity.Student;

import java.util.List;


/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface StudentService {
    List<Student> findAll();
    List<StudentCommand> findStudentCommandByName(String name);
    List<StudentCommand> findStudentCommandByPhone(String phone);
    List<StudentCommand> findStudentCommandByNameAndPhone(String name, String phone);
}
