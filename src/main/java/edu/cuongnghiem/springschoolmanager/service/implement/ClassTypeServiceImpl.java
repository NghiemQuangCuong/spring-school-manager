package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.command.ClassTypeCommand;
import edu.cuongnghiem.springschoolmanager.converters.ClassTypeConverter;
import edu.cuongnghiem.springschoolmanager.entity.ClassType;
import edu.cuongnghiem.springschoolmanager.repository.ClassTypeRepository;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class ClassTypeServiceImpl implements ClassTypeService {

    private final ClassTypeRepository classTypeRepository;
    private final ClassTypeConverter classTypeConverter;

    public ClassTypeServiceImpl(ClassTypeRepository classTypeRepository, ClassTypeConverter classTypeConverter) {
        this.classTypeRepository = classTypeRepository;
        this.classTypeConverter = classTypeConverter;
    }

    @Override
    public Set<String> getAllName() {
        Set<String> names = new HashSet<>();
        classTypeRepository.findAll().forEach(classType -> names.add(classType.getName()));
        return names;
    }

    @Override
    public List<ClassType> findAll() {
        return classTypeRepository.findAll();
    }

    @Override
    public List<ClassTypeCommand> findAllCommand() {
        return classTypeRepository.findAll().stream().map(classTypeConverter::entityToCommand).collect(Collectors.toList());
    }

    @Override
    public ClassTypeCommand findCommandById(Long id) {
        return classTypeConverter.entityToCommand(classTypeRepository.findById(id).orElse(null));
    }
}
