package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.ClassTypeCommand;
import edu.cuongnghiem.springschoolmanager.entity.ClassType;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class ClassTypeConverter implements EntityCommandConverter<ClassType, ClassTypeCommand> {
    @Override
    public ClassType commandToEntity(ClassTypeCommand command) {
        return null;
    }

    @Override
    public ClassTypeCommand entityToCommand(ClassType entity) {
        return null;
    }
}
