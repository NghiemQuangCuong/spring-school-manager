package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;

import java.util.List;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface TeacherService {
    Teacher getTeacherById(Long id);
    Teacher save(Teacher teacher);
    List<TeacherCommand> findTeacherCommandByName(String name);
    List<TeacherCommand> findTeacherCommandByPhone(String phone);
    List<TeacherCommand> findTeacherCommandByNameAndPhone(String name, String phone);
}
