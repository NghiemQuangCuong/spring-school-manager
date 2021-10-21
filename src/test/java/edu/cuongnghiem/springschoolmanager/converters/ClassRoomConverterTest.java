package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.ClassTypeCommand;
import edu.cuongnghiem.springschoolmanager.command.TeacherCommand;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.entity.ClassType;
import edu.cuongnghiem.springschoolmanager.entity.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassRoomConverterTest {

    private final Long CLASS_ROOM_ID = 123L;
    private final Long CLASS_ROOM_COMMAND_ID = 213L;
    private final String CLASS_ROOM_NAME = "Class Room Name";
    private final String CLASS_ROOM_COMMAND_NAME = "Class Room Command Name";
    private final Long CLASS_TYPE_ID = 347L;
    private final Long CLASS_TYPE_COMMAND_ID = 235L;
    private final Integer TEACHERS_SET_SIZE = 5;
    private final Integer TEACHERS_COMMAND_SET_SIZE = 8;
    private final ClassType CLASS_TYPE = ClassType.builder().id(CLASS_TYPE_ID).build();
    private final ClassTypeCommand CLASS_TYPE_COMMAND = ClassTypeCommand.builder().id(CLASS_TYPE_COMMAND_ID).build();

    ClassRoomConverter classRoomConverter;

    ClassRoom classRoom;
    ClassRoomCommand classRoomCommand;

    @BeforeEach
    void setUp() {
        classRoomConverter = new ClassRoomConverter(new ClassTypeConverter(), new TeacherConverter(new ContactConverter()));
        // Set-up for classRoom
        classRoom = ClassRoom.builder()
                .id(CLASS_ROOM_ID)
                .name(CLASS_ROOM_NAME)
                .classType(CLASS_TYPE)
                .build();
        Set<Teacher> teachers = new HashSet<>();
        for (long i = 0L; i < TEACHERS_SET_SIZE; i++) {
            teachers.add(Teacher.builder().id(i + 1).build());
        }
        classRoom.setTeachers(teachers);

        // Set-up for classRoomCommand
        classRoomCommand = ClassRoomCommand.builder()
                .id(CLASS_ROOM_COMMAND_ID)
                .name(CLASS_ROOM_COMMAND_NAME)
                .classTypeCommand(CLASS_TYPE_COMMAND)
                .build();
        Set<TeacherCommand> teacherCommands = new HashSet<>();
        for (long i = 0L; i < TEACHERS_COMMAND_SET_SIZE; i++) {
            teacherCommands.add(TeacherCommand.builder().id(i + 1).build());
        }
        classRoomCommand.setTeacherCommands(teacherCommands);
    }

    @Test
    void commandToEntity() {
        ClassRoom entity = classRoomConverter.commandToEntity(classRoomCommand);
        assertEquals(CLASS_ROOM_COMMAND_ID, entity.getId());
        assertEquals(CLASS_ROOM_COMMAND_NAME, entity.getName());
        assertEquals(CLASS_TYPE_COMMAND_ID, entity.getClassType().getId());
        assertEquals(TEACHERS_COMMAND_SET_SIZE, entity.getTeachers().size());
    }

    @Test
    void entityToCommand() {
        ClassRoomCommand command = classRoomConverter.entityToCommand(classRoom);
        assertEquals(CLASS_ROOM_ID, command.getId());
        assertEquals(CLASS_ROOM_NAME, command.getName());
        assertEquals(CLASS_TYPE_ID, command.getClassTypeCommand().getId());
        assertEquals(TEACHERS_SET_SIZE, command.getTeacherCommands().size());
    }
}