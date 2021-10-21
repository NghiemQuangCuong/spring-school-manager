package edu.cuongnghiem.springschoolmanager.command;

import lombok.Builder;
import lombok.Data;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@Builder
public class MarkCommand {
    private Long id;
    private Float score;
    private StudentCommand studentCommand;
    private ExamCommand examCommand;
}
