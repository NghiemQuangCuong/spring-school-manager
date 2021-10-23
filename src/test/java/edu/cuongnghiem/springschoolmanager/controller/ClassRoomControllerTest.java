package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClassRoomControllerTest {

    @InjectMocks
    ClassRoomController controller;
    @Mock
    ClassTypeService classTypeService;
    @Mock
    ClassRoomService classRoomService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(classTypeService.getAllName()).thenReturn(new HashSet<>());
    }

    @Test
    void getIndexPage() throws Exception {
        when(classRoomService.getClassRoomCommand()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/class"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("classTypes"))
                .andExpect(model().attributeExists("filter"))
                .andExpect(model().attributeExists("classes"))
                .andExpect(view().name("/class/index"));
    }

    @Test
    void getClassWithFilters() throws Exception {
        when(classRoomService.getClassRoomCommandByClassTypeNameAndByName(any(), anyString()))
                .thenReturn(new ArrayList<>());
        mockMvc.perform(post("/class")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("filter", "All")
                    .param("name", "Some name"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("classTypes"))
                .andExpect(model().attributeExists("filter"))
                .andExpect(model().attributeExists("classes"))
                .andExpect(view().name("/class/index"));
    }

    @Test
    void getClassDetails() throws Exception {
        mockMvc.perform(get("/class/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/class/details"))
                .andExpect(model().attributeExists("class"));
    }
}