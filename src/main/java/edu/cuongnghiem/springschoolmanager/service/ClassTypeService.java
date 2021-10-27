package edu.cuongnghiem.springschoolmanager.service;

import edu.cuongnghiem.springschoolmanager.command.ClassTypeCommand;
import edu.cuongnghiem.springschoolmanager.entity.ClassType;

import java.util.List;
import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface ClassTypeService {
    Set<String> getAllName();
    List<ClassType> findAll();
    List<ClassTypeCommand> findAllCommand();
    ClassTypeCommand findCommandById(Long id);
}
