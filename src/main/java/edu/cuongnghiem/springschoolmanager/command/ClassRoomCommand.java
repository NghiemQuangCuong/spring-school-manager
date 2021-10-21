package edu.cuongnghiem.springschoolmanager.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Getter
@Setter
@NoArgsConstructor
public class ClassRoomCommand {
    private Long id;
    private String name;
    private ClassTypeCommand classTypeCommand;
    private Set<TeacherCommand> teacherCommands = new HashSet<>();
}
