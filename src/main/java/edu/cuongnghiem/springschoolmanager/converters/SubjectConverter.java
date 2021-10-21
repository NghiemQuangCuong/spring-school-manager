package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.SubjectCommand;
import edu.cuongnghiem.springschoolmanager.entity.Subject;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class SubjectConverter implements EntityCommandConverter<Subject, SubjectCommand> {
    @Override
    public Subject commandToEntity(SubjectCommand command) {
        return null;
    }

    @Override
    public SubjectCommand entityToCommand(Subject entity) {
        return null;
    }
}
