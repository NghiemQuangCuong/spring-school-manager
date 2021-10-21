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
public class SchoolYearCommand {
    private Long id;
    private Integer firstYear;
    private Integer secondYear;
}
