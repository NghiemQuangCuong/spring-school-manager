package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.command.MarkCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.converters.MarkConverter;
import edu.cuongnghiem.springschoolmanager.converters.StudentConverter;
import edu.cuongnghiem.springschoolmanager.converters.SubjectConverter;
import edu.cuongnghiem.springschoolmanager.entity.ExamType;
import edu.cuongnghiem.springschoolmanager.entity.Student;
import edu.cuongnghiem.springschoolmanager.entity.Subject;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import edu.cuongnghiem.springschoolmanager.repository.StudentRepository;
import edu.cuongnghiem.springschoolmanager.repository.SubjectRepository;
import edu.cuongnghiem.springschoolmanager.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentConverter studentConverter;
    private final SubjectRepository subjectRepository;
    private final SubjectConverter subjectConverter;
    private final MarkConverter markConverter;

    public StudentServiceImpl(StudentRepository studentRepository, StudentConverter studentConverter, SubjectRepository subjectRepository, SubjectConverter subjectConverter, MarkConverter markConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
        this.subjectRepository = subjectRepository;
        this.subjectConverter = subjectConverter;
        this.markConverter = markConverter;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<StudentCommand> findStudentCommandByName(String name) {
        return studentRepository.findAll()
                .stream().filter(student ->
                        (
                                student.getFirstName().equalsIgnoreCase(name) ||
                                student.getLastName().equalsIgnoreCase(name) ||
                                (student.getFirstName() + " " + student.getLastName()).equalsIgnoreCase(name)
                                ))
                .map(studentConverter::entityToCommand)
                .sorted((s1, s2) -> (s1.getFirstName() + " " + s2.getFirstName()).compareTo(s2.getFirstName() + " " + s2.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentCommand> findStudentCommandByPhone(String phone) {
        return studentRepository.findAll()
                .stream().filter(student ->
                        (student.getContact().getPhone1().equals(phone) || student.getContact().getPhone2().equals(phone))
                )
                .map(studentConverter::entityToCommand).collect(Collectors.toList());
    }

    @Override
    public List<StudentCommand> findStudentCommandByNameAndPhone(String name, String phone) {
        return findStudentCommandByName(name)
                .stream().filter(student ->
                        (student.getContactCommand().getPhone1().equals(phone) || student.getContactCommand().getPhone2().equals(phone))
                ).collect(Collectors.toList());
    }

    @Override
    public StudentCommand findStudentCommandById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null)
            throw new NotFoundException("Student not found, id = " + id);
        return studentConverter.entityToCommand(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Map<String, Map<ExamType, MarkCommand>> getMarkMapSubjectKey(Long id) {
        // init map object
        Map<String, Map<ExamType, MarkCommand>> result = new HashMap<>();
        subjectRepository.findAll().forEach(subject -> {
            Map<ExamType, MarkCommand> map = new HashMap<>();
            for (ExamType type : ExamType.values())
                map.put(type, null);
            result.put(subject.getName(), map);
        });

        findStudentById(id).getMarks().forEach(mark -> {
            Subject subject = mark.getExam().getSubject();
            ExamType examType = mark.getExam().getExamType();
            result.get(subject.getName()).replace(examType, markConverter.entityToCommand(mark));
        });
        return result;
    }
}
