package edu.cuongnghiem.springschoolmanager.command;

import edu.cuongnghiem.springschoolmanager.entity.ExamType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
public class ExamCommand {
    private Long id;
    private SchoolYearCommand schoolYearCommand;
    private ExamType examType;
    private SubjectCommand subjectCommand;
}
