package edu.cuongnghiem.springschoolmanager.command;

import edu.cuongnghiem.springschoolmanager.entity.ExamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamCommand {
    private Long id;
    private SchoolYearCommand schoolYearCommand;
    private ExamType examType;
    private SubjectCommand subjectCommand;
}
