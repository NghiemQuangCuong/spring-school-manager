package edu.cuongnghiem.springschoolmanager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClassRoomCommandTest {

    ClassRoomCommand classRoomCommand;

    private final Long ID = 5L;
    private final String NAME = "Some name";
    private final ClassTypeCommand CLASS_TYPE_COMMAND = ClassTypeCommand.builder().id(3L).build();

    @BeforeEach
    void setUp() {
        classRoomCommand = ClassRoomCommand.builder().id(ID)
                .name(NAME)
                .classTypeCommand(CLASS_TYPE_COMMAND)
                .teacherCommands(null).build();
    }

    @Test
    void testBuilder() {
        assertEquals(ID, classRoomCommand.getId());
        assertEquals(NAME, classRoomCommand.getName());
        assertEquals(CLASS_TYPE_COMMAND.getId(), classRoomCommand.getClassTypeCommand().getId());
        assertNotNull(classRoomCommand.getTeacherCommands());
    }
}