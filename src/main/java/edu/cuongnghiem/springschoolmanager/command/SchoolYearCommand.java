package edu.cuongnghiem.springschoolmanager.command;

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
public class SchoolYearCommand {
    private Long id;
    private Integer firstYear;
    private Integer secondYear;
}
