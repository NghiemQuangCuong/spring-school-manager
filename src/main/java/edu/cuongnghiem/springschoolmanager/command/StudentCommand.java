package edu.cuongnghiem.springschoolmanager.command;

import lombok.Builder;
import lombok.Data;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@Builder
public class StudentCommand {
    private Long id;
    private String firstName;
    private String lastName;
    private ClassRoomCommand classRoomCommand;
    private ContactCommand contactCommand;
}
