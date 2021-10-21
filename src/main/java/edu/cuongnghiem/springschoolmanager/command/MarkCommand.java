package edu.cuongnghiem.springschoolmanager.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
public class MarkCommand {
    private Long id;
    private Float score;
    private StudentCommand studentCommand;
    private ExamCommand examCommand;
}
