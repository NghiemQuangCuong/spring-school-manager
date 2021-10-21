package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.SchoolYearCommand;
import edu.cuongnghiem.springschoolmanager.entity.SchoolYear;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class SchoolYearConverter implements EntityCommandConverter<SchoolYear, SchoolYearCommand> {
    @Override
    public SchoolYear commandToEntity(SchoolYearCommand command) {
        if (command == null)
            return null;
        return SchoolYear.builder()
                .id(command.getId())
                .firstYear(command.getFirstYear())
                .secondYear(command.getSecondYear())
                .build();
    }

    @Override
    public SchoolYearCommand entityToCommand(SchoolYear entity) {
        if (entity == null)
            return null;
        return SchoolYearCommand.builder()
                .id(entity.getId())
                .firstYear(entity.getFirstYear())
                .secondYear(entity.getSecondYear())
                .build();
    }
}
