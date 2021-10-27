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
public class StudentCommand {
    private Long id;
    @NotBlank(message = "First name must not be blank")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    private String lastName;
    private ClassRoomCommand classRoomCommand;
    @Valid
    private ContactCommand contactCommand;
}
