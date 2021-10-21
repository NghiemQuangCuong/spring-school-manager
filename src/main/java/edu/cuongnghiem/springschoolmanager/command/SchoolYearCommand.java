package edu.cuongnghiem.springschoolmanager.command;

import lombok.Builder;
import lombok.Data;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@Builder
public class SchoolYearCommand {
    private Long id;
    private Integer firstYear;
    private Integer secondYear;
}
