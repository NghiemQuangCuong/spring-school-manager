package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.converters.ClassRoomConverter;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.repository.ClassRoomRepository;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class ClassRoomServiceImpl implements ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final ClassRoomConverter classRoomConverter;

    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository, ClassRoomConverter classRoomConverter) {
        this.classRoomRepository = classRoomRepository;
        this.classRoomConverter = classRoomConverter;
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
}
