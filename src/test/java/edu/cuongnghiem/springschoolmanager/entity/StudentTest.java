package edu.cuongnghiem.springschoolmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentTest {

    Student student;

    private final Long ID = 1L;
    private final String FIRST_NAME = "First Name";
    private final String LAST_NAME = "Last Name";
    private final ClassRoom CLASSROOM = ClassRoom.builder().id(1L).build();
    private final Contact CONTACT = Contact.builder().address("Address").build();

    @BeforeEach
    void setUp() {
        student = Student.builder().id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .classRoom(CLASSROOM)
                .contact(CONTACT)
                .build();
    }

    @Test
    void testBuilder() {
        assertEquals(ID, student.getId());
        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(CLASSROOM.getId(), student.getClassRoom().getId());
        assertEquals(CONTACT.getAddress(), student.getContact().getAddress());
        assertNotNull(student.getMarks());
    }

}