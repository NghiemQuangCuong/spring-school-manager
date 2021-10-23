package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.command.ClassRoomCommand;

import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface ClassRoomService {
    Set<ClassRoomCommand> getClassRoomCommandByClassTypeName(String name);
    Set<ClassRoomCommand> getClassRoomCommand();
}
