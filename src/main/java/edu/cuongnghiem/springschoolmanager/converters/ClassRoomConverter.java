package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class ClassRoomConverter implements EntityCommandConverter<ClassRoom, ClassRoomCommand> {

    private final ClassTypeConverter classTypeConverter;
    private final TeacherConverter teacherConverter;

    public ClassRoomConverter(ClassTypeConverter classTypeConverter, TeacherConverter teacherConverter) {
        this.classTypeConverter = classTypeConverter;
        this.teacherConverter = teacherConverter;
    }

    @Override
    public ClassRoom commandToEntity(ClassRoomCommand command) {
        ClassRoom entity = ClassRoom.builder()
                .id(command.getId())
                .name(command.getName())
                .classType(classTypeConverter.commandToEntity(command.getClassTypeCommand()))
                .build();
        Set<Teacher> teachers = new HashSet<>();
        command.getTeacherCommands().forEach(teacherCommand ->
                teachers.add(teacherConverter.commandToEntity(teacherCommand)));
        entity.setTeachers(teachers);
        return entity;
    }

    @Override
    public ClassRoomCommand entityToCommand(ClassRoom entity) {
        ClassRoomCommand command = ClassRoomCommand.builder()
                .id(entity.getId())
                .name(entity.getName())
                .classTypeCommand(classTypeConverter.entityToCommand(entity.getClassType()))
                .build();
        Set<TeacherCommand> teacherCommands = new HashSet<>();
        entity.getTeachers().forEach(teacher ->
                teacherCommands.add(teacherConverter.entityToCommand(teacher)));
        command.setTeacherCommands(teacherCommands);
        return command;
    }
}
