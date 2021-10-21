package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.MarkCommand;
import edu.cuongnghiem.springschoolmanager.entity.Mark;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class MarkConverter implements EntityCommandConverter<Mark, MarkCommand> {

    private final StudentConverter studentConverter;
    private final ExamConverter examConverter;

    public MarkConverter(StudentConverter studentConverter, ExamConverter examConverter) {
        this.studentConverter = studentConverter;
        this.examConverter = examConverter;
    }

    @Override
    public Mark commandToEntity(MarkCommand command) {
        return Mark.builder()
                .id(command.getId())
                .score(command.getScore())
                .student(studentConverter.commandToEntity(command.getStudentCommand()))
                .exam(examConverter.commandToEntity(command.getExamCommand()))
                .build();
    }

    @Override
    public MarkCommand entityToCommand(Mark entity) {
        return MarkCommand.builder()
                .id(entity.getId())
                .score(entity.getScore())
                .studentCommand(studentConverter.entityToCommand(entity.getStudent()))
                .examCommand(examConverter.entityToCommand(entity.getExam()))
                .build();
    }
}
