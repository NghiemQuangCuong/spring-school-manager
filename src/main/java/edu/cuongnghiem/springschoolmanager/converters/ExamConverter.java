package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.ExamCommand;
import edu.cuongnghiem.springschoolmanager.entity.Exam;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class ExamConverter implements EntityCommandConverter<Exam, ExamCommand> {

    @Override
    public Exam commandToEntity(ExamCommand command) {
        return null;
    }

    @Override
    public ExamCommand entityToCommand(Exam entity) {
        return null;
    }
}
