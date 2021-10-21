package edu.cuongnghiem.springschoolmanager.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactTest {

    Contact contact;

    private final String PHONE1 = "PHONE1";
    private final String PHONE2 = "PHONE2";
    private final String ADDRESS = "Address";
    private final String CITY = "Some city";

    @BeforeEach
    void setUp() {
        contact = Contact.builder().phone1(PHONE1).phone2(PHONE2).address(ADDRESS).city(CITY).build();
    }

    @Test
    void testBuilder() {
        assertEquals(PHONE1, contact.getPhone1());
        assertEquals(PHONE2, contact.getPhone2());
        assertEquals(ADDRESS, contact.getAddress());
        assertEquals(CITY, contact.getCity());
    }
}