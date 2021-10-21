package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Component
public class TeacherConverter implements EntityCommandConverter<Teacher, TeacherCommand> {

    private final ContactConverter contactConverter;

    public TeacherConverter(ContactConverter contactConverter) {
        this.contactConverter = contactConverter;
    }

    @Override
    public Teacher commandToEntity(TeacherCommand command) {
        if (command == null)
            return null;
        return Teacher.builder()
                .id(command.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .contact(contactConverter.commandToEntity(command.getContactCommand()))
                .build();
    }

    @Override
    public TeacherCommand entityToCommand(Teacher entity) {
        if (entity == null)
            return null;
        return TeacherCommand.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .contactCommand(contactConverter.entityToCommand(entity.getContact()))
                .build();
    }
}
