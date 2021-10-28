package edu.cuongnghiem.springschoolmanager.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherCommand {
    private Long id;
    @NotBlank(message = "Teacher's firstname must not be blank")
    private String firstName;
    @NotBlank(message = "Teacher's lastname must not be blank")
    private String lastName;
    @Valid
    private ContactCommand contactCommand;
}
