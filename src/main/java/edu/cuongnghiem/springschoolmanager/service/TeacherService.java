package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.entity.Teacher;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface TeacherService {
    Teacher getTeacherById(Long id);
    Teacher save(Teacher teacher);
}
