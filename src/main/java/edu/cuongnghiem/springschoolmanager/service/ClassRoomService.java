package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.command.StudentCommand;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface ClassRoomService {
    ClassRoomCommand getClassRoomCommandById(Long id);
    List<ClassRoomCommand> getClassRoomCommandByClassTypeName(String name);
    List<ClassRoomCommand> getClassRoomCommand();
    List<ClassRoomCommand> getClassRoomCommandByClassTypeNameAndByName(String classTypeName, String name);
    ClassRoom getClassRoomById(Long id);
    ClassRoom saveClassRoom(ClassRoom classRoom);
    List<StudentCommand> getStudentsCommandFromClassRoomId(Long id);
    Page<StudentCommand> getStudentsCommandPagingFromClassRoomIdAndName(Long id, int page, int recordsPerPage, String name);
    long numberOfStudentsOfClassRoomId(Long id);
    List<ClassRoomCommand> getAllClassRoom();
}
