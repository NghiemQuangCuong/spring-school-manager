package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.entity.Student;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class StudentConverter implements EntityCommandConverter<Student, StudentCommand> {
    @Override
    public Student commandToEntity(StudentCommand command) {
        return null;
    }

    @Override
    public StudentCommand entityToCommand(Student entity) {
        return null;
    }
}
