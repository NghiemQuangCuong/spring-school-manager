package edu.cuongnghiem.springschoolmanager.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassTypeCommandTest {

    ClassTypeCommand classTypeCommand;

    private final Long ID = 2L;
    private final String NAME = "Some Name";

    @BeforeEach
    void setUp() {
        classTypeCommand = ClassTypeCommand.builder().id(ID).name(NAME).build();
    }

    @Test
    void testBuilder() {
        assertEquals(ID, classTypeCommand.getId());
        assertEquals(NAME, classTypeCommand.getName());
    }
}