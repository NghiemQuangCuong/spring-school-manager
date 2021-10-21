package edu.cuongnghiem.springschoolmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClassRoomTest {

    ClassRoom classRoom;

    private final Long ID = 1L;
    private final String NAME = "A13";

    @BeforeEach
    void setUp() {
        classRoom = ClassRoom.builder().id(ID).name(NAME).build();
    }

    @Test
    void testBuilderDesignPattern() {
        assertEquals(ID, classRoom.getId());
        assertEquals(NAME, classRoom.getName());
    }

    @Test
    void testBuilderDesignPatternFail() {
        assertNotNull(classRoom.getStudents());
        assertNotNull(classRoom.getTeachers());
    }
}