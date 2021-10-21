package edu.cuongnghiem.springschoolmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClassTypeTest {

    ClassType classType;

    private final Long ID = 1L;
    private final String NAME = "Maths";

    @BeforeEach
    void setUp() {
        classType = ClassType.builder().id(ID).name(NAME).build();
    }

    @Test
    void testBuilder() {
        assertEquals(ID, classType.getId());
        assertEquals(NAME, classType.getName());
        assertNotNull(classType.getClass());
    }
}