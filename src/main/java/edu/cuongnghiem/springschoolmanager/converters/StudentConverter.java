package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.entity.Student;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class StudentConverter implements EntityCommandConverter<Student, StudentCommand> {

    private final ClassRoomConverter classRoomConverter;
    private final ContactConverter contactConverter;

    public StudentConverter(ClassRoomConverter classRoomConverter, ContactConverter contactConverter) {
        this.classRoomConverter = classRoomConverter;
        this.contactConverter = contactConverter;
    }

    @Override
    public Student commandToEntity(StudentCommand command) {
        return Student.builder()
                .id(command.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .classRoom(classRoomConverter.commandToEntity(command.getClassRoomCommand()))
                .contact(contactConverter.commandToEntity(command.getContactCommand()))
                .build();
    }

    @Override
    public StudentCommand entityToCommand(Student entity) {
        return StudentCommand.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .classRoomCommand(classRoomConverter.entityToCommand(entity.getClassRoom()))
                .contactCommand(contactConverter.entityToCommand(entity.getContact()))
                .build();
    }
}
