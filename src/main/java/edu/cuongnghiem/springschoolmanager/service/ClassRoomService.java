package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;
import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;

import java.util.List;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface ClassRoomService {
    List<ClassRoomCommand> getClassRoomCommandByClassTypeName(String name);
    List<ClassRoomCommand> getClassRoomCommand();
    List<ClassRoomCommand> getClassRoomCommandByClassTypeNameAndByName(String classTypeName, String name);
    ClassRoom getClassRoomById(Long id);
    ClassRoom saveClassRoom(ClassRoom classRoom);
}
