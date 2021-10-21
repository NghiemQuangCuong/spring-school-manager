package edu.cuongnghiem.springschoolmanager.command;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Data
public class ClassRoomCommand {

    @Builder
    public ClassRoomCommand(Long id, String name, ClassTypeCommand classTypeCommand, Set<TeacherCommand> teacherCommands) {
        this.id = id;
        this.name = name;
        this.classTypeCommand = classTypeCommand;
        if (teacherCommands != null)
            this.teacherCommands = teacherCommands;
    }

    private Long id;
    private String name;
    private ClassTypeCommand classTypeCommand;
    private Set<TeacherCommand> teacherCommands = new HashSet<>();
}
