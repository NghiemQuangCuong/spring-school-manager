package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.*;
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
    }

    @Test
    void getIndexPage() throws Exception {
        when(classTypeService.getAllName()).thenReturn(new HashSet<>());
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
        when(classTypeService.getAllName()).thenReturn(new HashSet<>());
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
        when(classRoomService.getClassRoomCommandById(anyLong())).thenReturn(new ClassRoomCommand());
        when(classRoomService.getStudentsCommandPagingFromClassRoomId(1L, 1, 10))
                .thenReturn(new PageImpl<StudentCommand>(new ArrayList<>()));
        mockMvc.perform(get("/class/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/class/details"))
                .andExpect(model().attributeExists("class", "students", "page", "totalPage"
                , "totalStudent", "recordPerPage"));
    }
}