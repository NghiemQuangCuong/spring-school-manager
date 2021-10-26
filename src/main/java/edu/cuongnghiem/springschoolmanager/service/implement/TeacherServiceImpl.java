package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.converters.ClassRoomConverter;
import edu.cuongnghiem.springschoolmanager.converters.TeacherConverter;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import edu.cuongnghiem.springschoolmanager.repository.TeacherRepository;
import edu.cuongnghiem.springschoolmanager.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherConverter teacherConverter;
    private final ClassRoomConverter classRoomConverter;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherConverter teacherConverter, ClassRoomConverter classRoomConverter) {
        this.teacherRepository = teacherRepository;
        this.teacherConverter = teacherConverter;
        this.classRoomConverter = classRoomConverter;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public TeacherCommand findTeacherCommandById(Long id) {
        return teacherConverter.entityToCommand(teacherRepository.findById(id).orElse(null));
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<TeacherCommand> findTeacherCommandByName(String name) {
        return teacherRepository.findAll().stream().filter(teacher ->
            (teacher.getFirstName().equalsIgnoreCase(name) ||
                    teacher.getLastName().equalsIgnoreCase(name) ||
                    (teacher.getFirstName() + " " + teacher.getLastName()).equalsIgnoreCase(name))
        ).map(teacherConverter::entityToCommand).collect(Collectors.toList());
    }

    @Override
    public List<TeacherCommand> findTeacherCommandByPhone(String phone) {
        return teacherRepository.findAll().stream()
                .filter(teacher -> (teacher.getContact().getPhone1().equals(phone) || teacher.getContact().getPhone2().equals(phone)))
                .map(teacherConverter::entityToCommand)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherCommand> findTeacherCommandByNameAndPhone(String name, String phone) {
        return findTeacherCommandByName(name).stream()
                .filter(teacherCommand -> (teacherCommand.getContactCommand().getPhone1().equals(phone) || teacherCommand.getContactCommand().getPhone2().equals(phone)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClassRoomCommand> findClassRoomTeachByThisTeacher(Long id) {
        Set<ClassRoom> classRooms = getTeacherById(id).getClasses();
        List<ClassRoomCommand> result = new ArrayList<>();
        classRooms.forEach(classRoom -> {
            ClassRoomCommand command = classRoomConverter.entityToCommand(classRoom);
            command.setNumberOfStudents((long)classRoom.getStudents().size());
            command.setNumberOfTeachers((long)classRoom.getTeachers().size());
            result.add(command);
        });
        return result;
    }
}
