package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.ExamCommand;
import edu.cuongnghiem.springschoolmanager.entity.Exam;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class ExamConverter implements EntityCommandConverter<Exam, ExamCommand> {

    private final SchoolYearConverter schoolYearConverter;
    private final SubjectConverter subjectConverter;

    public ExamConverter(SchoolYearConverter schoolYearConverter, SubjectConverter subjectConverter) {
        this.schoolYearConverter = schoolYearConverter;
        this.subjectConverter = subjectConverter;
    }

    @Override
    public Exam commandToEntity(ExamCommand command) {
        if (command == null)
            return null;
        return Exam.builder()
                .id(command.getId())
                .schoolYear(schoolYearConverter.commandToEntity(command.getSchoolYearCommand()))
                .examType(command.getExamType())
                .subject(subjectConverter.commandToEntity(command.getSubjectCommand()))
                .build();
    }

    @Override
    public ExamCommand entityToCommand(Exam entity) {
        if (entity == null)
            return null;
        return ExamCommand.builder()
                .id(entity.getId())
                .schoolYearCommand(schoolYearConverter.entityToCommand(entity.getSchoolYear()))
                .examType(entity.getExamType())
                .subjectCommand(subjectConverter.entityToCommand(entity.getSubject()))
                .build();
    }
}
