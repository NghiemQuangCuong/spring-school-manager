package edu.cuongnghiem.springschoolmanager.command;

import edu.cuongnghiem.springschoolmanager.entity.ExamType;
import lombok.Builder;
import lombok.Data;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@Builder
public class ExamCommand {
    private Long id;
    private SchoolYearCommand schoolYearCommand;
    private ExamType examType;
    private SubjectCommand subjectCommand;
}
