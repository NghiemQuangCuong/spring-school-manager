package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import edu.cuongnghiem.springschoolmanager.repository.TeacherRepository;
import edu.cuongnghiem.springschoolmanager.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
