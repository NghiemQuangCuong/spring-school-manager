package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.converters.StudentConverter;
import edu.cuongnghiem.springschoolmanager.entity.Student;
import edu.cuongnghiem.springschoolmanager.repository.StudentRepository;
import edu.cuongnghiem.springschoolmanager.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;

    public StudentServiceImpl(StudentRepository studentRepository, StudentConverter studentConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentCommand> findStudentCommandByName(String name) {
        List<StudentCommand> result = new ArrayList<>();
        List<Student> students =
                studentRepository.findAll()
                        .stream().filter(student ->
                                (
                                        student.getFirstName().equalsIgnoreCase(name) ||
                                        student.getLastName().equalsIgnoreCase(name) ||
                                        (student.getFirstName() + " " + student.getLastName()).equalsIgnoreCase(name)
                                        ))
                        .collect(Collectors.toList());
        students.forEach(student -> {
            result.add(studentConverter.entityToCommand(student));
                });
        result.sort(Comparator.comparing(s -> (s.getFirstName() + " " + s.getLastName())));
        return result;
    }

    @Override
    public List<StudentCommand> findStudentCommandByPhone(String phone) {
        List<StudentCommand> result = new ArrayList<>();
        List<Student> students = studentRepository.findAll()
                .stream().filter(student ->
                        (student.getContact().getPhone1().equals(phone) || student.getContact().getPhone2().equals(phone))
                )
                .collect(Collectors.toList());
        students.forEach(student -> {
            result.add(studentConverter.entityToCommand(student));
        });
        return result;
    }

    @Override
    public List<StudentCommand> findStudentCommandByNameAndPhone(String name, String phone) {
        List<StudentCommand> result =
                findStudentCommandByName(name)
                        .stream().filter(student ->
                                (student.getContactCommand().getPhone1().equals(phone) || student.getContactCommand().getPhone2().equals(phone))
                        ).collect(Collectors.toList());
        return result;
    }
}
