package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.repository.ClassTypeRepository;
import edu.cuongnghiem.springschoolmanager.service.ClassTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Service
@Transactional
public class ClassTypeServiceImpl implements ClassTypeService {

    private final ClassTypeRepository classTypeRepository;

    public ClassTypeServiceImpl(ClassTypeRepository classTypeRepository) {
        this.classTypeRepository = classTypeRepository;
    }

    @Override
    public Set<String> getAllName() {
        Set<String> names = new HashSet<>();
        classTypeRepository.findAll().forEach(classType -> names.add(classType.getName()));
        return names;
    }
}
