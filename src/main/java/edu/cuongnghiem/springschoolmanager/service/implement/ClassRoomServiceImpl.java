package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.converters.ClassRoomConverter;
import edu.cuongnghiem.springschoolmanager.converters.StudentConverter;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import edu.cuongnghiem.springschoolmanager.repository.ClassRoomRepository;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final ClassRoomConverter classRoomConverter;
    private final StudentConverter studentConverter;

    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository, ClassRoomConverter classRoomConverter, StudentConverter studentConverter) {
        this.classRoomRepository = classRoomRepository;
        this.classRoomConverter = classRoomConverter;
        this.studentConverter = studentConverter;
    }

    @Override
    public ClassRoomCommand getClassRoomCommandById(Long id) {
        return classRoomConverter.entityToCommand(classRoomRepository.findById(id).orElse(null));
    }

    @Override
    public List<ClassRoomCommand> getClassRoomCommandByClassTypeName(String name) {
        List<ClassRoomCommand> result = new ArrayList<>();
        List<ClassRoom> classRoomList = classRoomRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        classRoomList.forEach(classRoom -> {
            if (classRoom.getClassType().getName().equals(name)) {
                ClassRoomCommand command = classRoomConverter.entityToCommand(classRoom);
                command.setNumberOfTeachers((long) classRoom.getTeachers().size());
                command.setNumberOfStudents((long) classRoom.getStudents().size());
                result.add(command);
            }
        });
        return result;
    }

    @Override
    public List<ClassRoomCommand> getClassRoomCommand() {
        List<ClassRoomCommand> result = new ArrayList<>();
        List<ClassRoom> classRoomList = classRoomRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        classRoomList.forEach(classRoom -> {
                    ClassRoomCommand command = classRoomConverter.entityToCommand(classRoom);
                    command.setNumberOfTeachers((long) classRoom.getTeachers().size());
                    command.setNumberOfStudents((long) classRoom.getStudents().size());
                    result.add(command);
                }
               );
        return result;
    }

    @Override
    public List<ClassRoomCommand> getClassRoomCommandByClassTypeNameAndByName(String classTypeName, String name) {
        List<ClassRoomCommand> result = new ArrayList<>();
        List<ClassRoom> classRoomList = classRoomRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        classRoomList.forEach(classRoom -> {
            if (classRoom.getName() != null && (classTypeName == null || classTypeName.equals(classRoom.getClassType().getName()))
                    && classRoom.getName().contains(name))
            {
                ClassRoomCommand command = classRoomConverter.entityToCommand(classRoom);
                command.setNumberOfTeachers((long) classRoom.getTeachers().size());
                command.setNumberOfStudents((long) classRoom.getStudents().size());
                result.add(command);
            }
        });
        return result;
    }

    @Override
    public ClassRoom getClassRoomById(Long id) {
        return classRoomRepository.findById(id).orElse(null);
    }

    @Override
    public ClassRoom saveClassRoom(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @Override
    public List<StudentCommand> getStudentsCommandFromClassRoomId(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElse(null);
        if (classRoom == null)
            return new ArrayList<>();
        List<StudentCommand> result = new ArrayList<>();
        classRoom.getStudents().forEach(student -> {
            result.add(studentConverter.entityToCommand(student));
        });
        result.sort((s1, s2) -> {
            if (s1.getId() > s2.getId())
                return 1;
            if (s1.getId() < s2.getId())
                return -1;
            return 0;
        });
        return result;
    }

    @Override
    public Page<StudentCommand> getStudentsCommandPagingFromClassRoomIdAndName(Long id, int page, int recordsPerPage, String name) {
        List<StudentCommand> list =
                getStudentsCommandFromClassRoomId(id);
        List<StudentCommand> studentCommands = list.stream().filter(studentCommand -> {
            String fullName = studentCommand.getFirstName() + ' ' + studentCommand.getLastName();
            if (fullName.toLowerCase().contains(name.toLowerCase()))
                return true;
            return false;
        }).collect(Collectors.toList());
        if (studentCommands.size() == 0)
            return new PageImpl<>(new ArrayList<>());
        if (page > getTotalPage(studentCommands.size(), recordsPerPage) || page <= 0)
            return null;
        boolean finalPage = (page == getTotalPage(studentCommands.size(), recordsPerPage));
        int min = recordsPerPage*(page-1);
        int max = (finalPage) ? studentCommands.size() : recordsPerPage*page ;
        Page<StudentCommand> studentPage =
                new PageImpl<>(studentCommands.subList(min, max), PageRequest.of(page-1, recordsPerPage), studentCommands.size());
        return studentPage;
    }

    @Override
    public long numberOfStudentsOfClassRoomId(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id).orElse(null);
        if (classRoom == null)
            throw new NotFoundException("Class Room not found, id = " + id);
        return (long) classRoom.getStudents().size();
    }

    @Override
    public boolean isUniqueClassRoomName(String name) {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        for (ClassRoom cr : classRooms)
            if (cr.getName().equals(name))
                return false;
        return true;
    }

    @Override
    public void deleteClassRoom(Long id) {
        ClassRoom classRoom = getClassRoomById(id);
        classRoom.getStudents().forEach(student -> {
            student.setClassRoom(null);
        });
        classRoom.setTeachers(new HashSet<>());
        classRoomRepository.delete(classRoom);
    }

    @Override
    public List<ClassRoomCommand> getAllClassRoom() {
        return classRoomRepository.findAll().stream()
                .map(classRoomConverter::entityToCommand)
                .sorted(Comparator.comparing(ClassRoomCommand::getName))
                .collect(Collectors.toList());
    }

    private int getTotalPage(int size, int rpp) {
        if (size % rpp != 0)
            return (size/rpp) + 1;
        else
            return (size / rpp);
    }
}
