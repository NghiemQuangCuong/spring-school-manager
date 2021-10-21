package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Component
public class TeacherConverter implements EntityCommandConverter<Teacher, TeacherCommand> {
    @Override
    public Teacher commandToEntity(TeacherCommand command) {
        return null;
    }

    @Override
    public TeacherCommand entityToCommand(Teacher entity) {
        return null;
    }
}
