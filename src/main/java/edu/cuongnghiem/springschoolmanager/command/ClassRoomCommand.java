package edu.cuongnghiem.springschoolmanager.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 50, message = "Name's length must between 3 and 50 characters")
    private String name;

    private ClassTypeCommand classTypeCommand;
    private Set<TeacherCommand> teacherCommands = new HashSet<>();

    // Additional fields for some service. Not all command object has these. Information display only.
    private Long numberOfTeachers;
    private Long numberOfStudents;
}
