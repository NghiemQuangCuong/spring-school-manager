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
        return null;
    }

    @Override
    public SchoolYearCommand entityToCommand(SchoolYear entity) {
        return null;
    }
}
